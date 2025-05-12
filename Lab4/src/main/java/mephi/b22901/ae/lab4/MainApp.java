
package mephi.b22901.ae.lab4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp extends JFrame {
    public MainApp() {
        setTitle("Магазин волшебных палочек «Олливандеры»");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton deliveryButton = new JButton("Прием поставки компонентов");
        JButton wandCreateButton = new JButton("Создать волшебную палочку");
        JButton wandSellButton = new JButton("Продать палочку");
        JButton warehouseButton = new JButton("Посмотреть состояние склада");
        JButton clearDataButton = new JButton("Полная очистка системы");
        JButton viewSoldButton = new JButton("Посмотреть проданные палочки");

        deliveryButton.addActionListener(e -> new DeliveryForm().setVisible(true));
        wandCreateButton.addActionListener(e -> new CreateWandForm().setVisible(true));
        wandSellButton.addActionListener(e -> new SellWandForm().setVisible(true));
        warehouseButton.addActionListener(e -> new WarehouseView().setVisible(true));
        clearDataButton.addActionListener(e -> confirmAndClearData());
        viewSoldButton.addActionListener(e -> new SoldWandForm().setVisible(true));
        
        
        panel.add(viewSoldButton);
        panel.add(deliveryButton);
        panel.add(wandCreateButton);
        panel.add(wandSellButton);
        panel.add(warehouseButton);
        panel.add(clearDataButton);

        add(panel);
    }

    private void confirmAndClearData() {
        int result = JOptionPane.showConfirmDialog(this,
                "Вы уверены, что хотите удалить все данные?",
                "Подтверждение",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            new CleanupService().clearAllData();
            JOptionPane.showMessageDialog(this, "Все данные успешно удалены.");
        }
    }

   
}
