
package mephi.b22901.ae.lab4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellWandForm extends JFrame {
    private JTextField wandIdField;
    private JTextField buyerNameField;

    public SellWandForm() {
        setTitle("Продажа палочки");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel idLabel = new JLabel("ID палочки:");
        wandIdField = new JTextField(20);

        JLabel buyerLabel = new JLabel("Имя покупателя:");
        buyerNameField = new JTextField(20);

        JButton sellButton = new JButton("Продать палочку");

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WandService wandService = new WandService();
                try {
                    int wandId = Integer.parseInt(wandIdField.getText());
                    String buyerName = buyerNameField.getText();

                    wandService.sellWand(wandId, buyerName);

                    JOptionPane.showMessageDialog(SellWandForm.this, "Палочка №" + wandId + " продана!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SellWandForm.this, "Ошибка: " + ex.getMessage());
                }
            }
        });

        add(idLabel);
        add(wandIdField);
        add(buyerLabel);
        add(buyerNameField);
        add(sellButton);
    }
}
