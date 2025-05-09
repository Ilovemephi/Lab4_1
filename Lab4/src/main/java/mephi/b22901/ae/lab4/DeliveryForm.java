
package mephi.b22901.ae.lab4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class DeliveryForm extends JFrame {
    private JTextField dateField;
    private JTextField componentTypeField;
    private JTextField componentNameField;
    private JTextField amountField;

    public DeliveryForm() {
        setTitle("Добавление поставки");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel dateLabel = new JLabel("Дата поставки (YYYY-MM-DD):");
        dateField = new JTextField(20);

        JLabel typeLabel = new JLabel("Тип компонента (wood/core):");
        componentTypeField = new JTextField(20);

        JLabel nameLabel = new JLabel("Название компонента:");
        componentNameField = new JTextField(20);

        JLabel amountLabel = new JLabel("Количество:");
        amountField = new JTextField(20);

        JButton submitButton = new JButton("Добавить поставку");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ComponentDAO componentDAO = new ComponentDAO();
                    DeliveryDAO deliveryDAO = new DeliveryDAO();
                    DeliveryDetailsDAO detailsDAO = new DeliveryDetailsDAO();
                    WarehouseService warehouseService = new WarehouseService();

               
                    String type = componentTypeField.getText();
                    String name = componentNameField.getText();
                    Component component = new Component(type, name);
                    componentDAO.create(component); 

      
                    LocalDate date = LocalDate.parse(dateField.getText());
                    Delivery delivery = new Delivery(0, date); 
                    deliveryDAO.create(delivery); // Здесь должен быть метод с RETURNING id_delivery

        
                    DeliveryDetails detail = new DeliveryDetails(
                            0, delivery, component.getIdComponent(), type, name, Integer.parseInt(amountField.getText())
                    );
                    detailsDAO.create(detail);

         
                    warehouseService.increaseStock(component, Integer.parseInt(amountField.getText()));

                    JOptionPane.showMessageDialog(DeliveryForm.this, "Поставка добавлена.");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DeliveryForm.this, "Ошибка: " + ex.getMessage());
                }
            }
        });

        add(dateLabel);
        add(dateField);
        add(typeLabel);
        add(componentTypeField);
        add(nameLabel);
        add(componentNameField);
        add(amountLabel);
        add(amountField);
        add(submitButton);
    }
}
