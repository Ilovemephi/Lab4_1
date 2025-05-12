
package mephi.b22901.ae.lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliveryForm extends JFrame {
    private JComboBox<String> typeComboBox;
    private JTextField componentNameField;
    private JTextField amountField;
    private JTextField dateField;
    private Map<String, String> typeMapping;
    private DefaultListModel<ComponentInput> componentInputs;
    private JList<ComponentInput> componentInputList;

    public DeliveryForm() {
        typeMapping = new HashMap<>();
        typeMapping.put("древесина", "wood");
        typeMapping.put("сердцевина", "core");

        setTitle("Добавление поставки");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

 
        JLabel typeLabel = new JLabel("Выберите тип компонента:");
        String[] displayTypes = {"древесина", "сердцевина"};
        typeComboBox = new JComboBox<>(displayTypes);

   
        JLabel nameLabel = new JLabel("Название компонента:");
        componentNameField = new JTextField(20);

   
        JLabel amountLabel = new JLabel("Количество:");
        amountField = new JTextField(20);

        
        JLabel dateLabel = new JLabel("Дата поставки (YYYY-MM-DD):");
        dateField = new JTextField(LocalDate.now().toString(), 20);


        componentInputs = new DefaultListModel<>();
        componentInputList = new JList<>(componentInputs);
        JScrollPane scrollPane = new JScrollPane(componentInputList);


        JButton addComponentButton = new JButton("Добавить компонент в поставку");
        addComponentButton.addActionListener(e -> {
            try {
                String displayType = (String) typeComboBox.getSelectedItem();
                String internalType = typeMapping.get(displayType);
                String name = componentNameField.getText().trim();
                int amount = Integer.parseInt(amountField.getText().trim());

                if (name.isEmpty()) {
                    throw new Exception("Введите название компонента.");
                }

                componentInputs.addElement(new ComponentInput(internalType, name, amount));
                JOptionPane.showMessageDialog(this, "Компонент добавлен в поставку.");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка: введите корректное количество.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
            }
        });

       
        JButton submitButton = new JButton("Зарегистрировать поставку");
        submitButton.addActionListener(e -> {
            try {
                ComponentDAO componentDAO = new ComponentDAO();
                DeliveryDAO deliveryDAO = new DeliveryDAO();
                DeliveryDetailsDAO detailsDAO = new DeliveryDetailsDAO();
                WarehouseService warehouseService = new WarehouseService();

                LocalDate date = LocalDate.parse(dateField.getText());
                Delivery delivery = new Delivery(0, date);
                deliveryDAO.create(delivery); 

                for (int i = 0; i < componentInputs.size(); i++) {
                    ComponentInput input = componentInputs.get(i);
                    Component component = componentDAO.getByTypeAndName(input.type, input.name);

                    if (component == null) {
                        component = new Component(input.type, input.name);
                        componentDAO.create(component);
                    }

             
                    DeliveryDetails detail = new DeliveryDetails(
                            0,
                            delivery,
                            component.getIdComponent(),
                            component.getComponentType(),
                            component.getComponentName(),
                            input.amount
                    );
                    detailsDAO.create(detail);

             
                    warehouseService.increaseStock(component, input.amount);
                }

                JOptionPane.showMessageDialog(this, "Поставка успешно зарегистрирована.");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ошибка при регистрации поставки:\n" + ex.getMessage());
            }
        });


        JPanel topPanel = new JPanel(new GridLayout(4, 2));
        topPanel.add(typeLabel);
        topPanel.add(typeComboBox);
        topPanel.add(nameLabel);
        topPanel.add(componentNameField);
        topPanel.add(amountLabel);
        topPanel.add(amountField);
        topPanel.add(addComponentButton);
        add(topPanel);
        add(new JLabel("Добавленные компоненты:"));
        add(scrollPane);
        add(dateLabel);
        add(dateField);
        add(submitButton);
    }


    private static class ComponentInput {
        String type;
        String name;
        int amount;

        public ComponentInput(String type, String name, int amount) {
            this.type = type;
            this.name = name;
            this.amount = amount;
        }

        @Override
        public String toString() {
            String displayType = type.equals("wood") ? "древесина" : "сердцевина";
            return displayType + " " + name + " — " + amount + " шт.";
        }
    }
}