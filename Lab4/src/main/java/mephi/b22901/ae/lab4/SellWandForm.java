
package mephi.b22901.ae.lab4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SellWandForm extends JFrame {
    private JComboBox<WandWrapper> wandComboBox;
    private JTextField buyerNameField;

    public SellWandForm() {
        setTitle("Продажа волшебной палочки");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        WandService wandService = new WandService();
        List<Wand> availableWands = wandService.getAvailableWands();

        if (availableWands.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Нет доступных палочек для продажи.");
            add(new JLabel("Нет доступных палочек для продажи"));
            JButton backButton = new JButton("Назад");
            backButton.addActionListener(e -> dispose());
            add(backButton);
            return;
        }


        DefaultComboBoxModel<WandWrapper> model = new DefaultComboBoxModel<>();

        for (Wand wand : availableWands) {
            model.addElement(new WandWrapper(wand));
        }

        wandComboBox = new JComboBox<>(model);


        JLabel buyerLabel = new JLabel("Имя покупателя:");
        buyerNameField = new JTextField(20);

        JButton sellButton = new JButton("Продать выбранную палочку");

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Wand selectedWand = wandComboBox.getItemAt(wandComboBox.getSelectedIndex()).getWand();
                    String buyerName = buyerNameField.getText().trim();

                    if (buyerName.isEmpty()) {
                        throw new Exception("Введите имя покупателя.");
                    }


                    WandService wandService = new WandService();
                    wandService.sellWand(selectedWand.getIdWand(), buyerName);

                    JOptionPane.showMessageDialog(SellWandForm.this, "Палочка:\n" + selectedWand.toString() + "\nпродана " + buyerName);
                    dispose(); 
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SellWandForm.this, "Ошибка: " + ex.getMessage());
                }
            }
        });


        add(new JLabel("Выберите палочку для продажи:"));
        add(wandComboBox);
        add(buyerLabel);
        add(buyerNameField);
        add(sellButton);
    }
}

class WandWrapper {
    private final Wand wand;

    public WandWrapper(Wand wand) {
        this.wand = wand;
    }

    public Wand getWand() {
        return wand;
    }

    @Override
    public String toString() {
        return "Палочка №" + wand.getIdWand() +
               ": " + wand.getWood().getComponentName() +
               " (" + wand.getWood().getComponentType() + ") + " +
               wand.getCore().getComponentName() +
               " (" + wand.getCore().getComponentType() + ")" +
               " — Статус: " + wand.getStatus();
    }
}