
package mephi.b22901.ae.lab4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DeliveryForm extends JFrame {
    private JComboBox<String> typeComboBox;
    private JTextField componentNameField;
    private JTextField amountField;
    private JTextField dateField;
    private Map<String, String> typeMapping;

    public DeliveryForm() {
        typeMapping = new HashMap<>();
        typeMapping.put("древесина", "wood");
        typeMapping.put("сердцевина", "core");

        setTitle("Добавление поставки");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel typeLabel = new JLabel("Выберите тип компонента:");
        String[] displayTypes = {"древесина", "сердцевина"};
        typeComboBox = new JComboBox<>(displayTypes);

        JLabel nameLabel = new JLabel("Название компонента:");
        componentNameField = new JTextField(20);

        JLabel dateLabel = new JLabel("Дата поставки (YYYY-MM-DD):");
        dateField = new JTextField(LocalDate.now().toString(), 20);

        JLabel amountLabel = new JLabel("Количество:");
        amountField = new JTextField(20);

        JButton submitButton = new JButton("Добавить поставку");

        submitButton.addActionListener(e -> {
            try {
                ComponentDAO componentDAO = new ComponentDAO();
                DeliveryDAO deliveryDAO = new DeliveryDAO();
                DeliveryDetailsDAO detailsDAO = new DeliveryDetailsDAO();
                WarehouseService warehouseService = new WarehouseService();

             
                String displayType = (String) typeComboBox.getSelectedItem(); 
                String internalType = typeMapping.get(displayType); 

                String name = componentNameField.getText().trim();
                int amount = Integer.parseInt(amountField.getText().trim());
                LocalDate date = LocalDate.parse(dateField.getText());

  
                Component component = componentDAO.getByTypeAndName(internalType, name);
                if (component == null) {
                    component = new Component(internalType, name);
                    componentDAO.create(component);
                }


                Delivery delivery = new Delivery(0, date);
                deliveryDAO.create(delivery); 

      
                DeliveryDetails detail = new DeliveryDetails(
                        0,
                        delivery,
                        component.getIdComponent(),
                        component.getComponentType(),
                        component.getComponentName(),
                        amount
                );
                detailsDAO.create(detail);


                warehouseService.increaseStock(component, amount);

                JOptionPane.showMessageDialog(DeliveryForm.this, "Поставка успешно добавлена.");
                dispose(); 

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(DeliveryForm.this, "Ошибка при добавлении поставки:\n" + ex.getMessage());
            }
        });


        add(typeLabel);
        add(typeComboBox);
        add(nameLabel);
        add(componentNameField);
        add(dateLabel);
        add(dateField);
        add(amountLabel);
        add(amountField);
        add(submitButton);
    }
}