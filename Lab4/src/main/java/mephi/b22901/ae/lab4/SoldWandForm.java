
package mephi.b22901.ae.lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SoldWandForm extends JFrame {

    public SoldWandForm() {
        setTitle("Проданные палочки");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        WandDAO wandDAO = new WandDAO();
        List<Wand> allWands = wandDAO.getAll();
        List<Wand> soldWands = new java.util.ArrayList<>();


        for (Wand wand : allWands) {
            if ("продано".equals(wand.getStatus())) {
                soldWands.add(wand);
            }
        }


        if (soldWands.isEmpty()) {
            JTextArea area = new JTextArea("Нет проданных палочек.");
            area.setEditable(false);
            add(new JScrollPane(area));
        } else {

            String[] columnNames = {"ID", "Древесина", "Сердцевина", "Имя покупателя"};
            Object[][] data = new Object[soldWands.size()][4];

            for (int i = 0; i < soldWands.size(); i++) {
                Wand wand = soldWands.get(i);
                data[i][0] = wand.getIdWand();
                data[i][1] = wand.getWood().getComponentName() + " (" + wand.getWood().getComponentType() + ")";
                data[i][2] = wand.getCore().getComponentName() + " (" + wand.getCore().getComponentType() + ")";
                data[i][3] = wand.getOwnerName();
            }

            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);
        }

  
        JButton closeButton = new JButton("Закрыть");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}