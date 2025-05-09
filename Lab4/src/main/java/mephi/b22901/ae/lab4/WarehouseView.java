
package mephi.b22901.ae.lab4;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WarehouseView extends JFrame {

    public WarehouseView() {
        setTitle("Состояние склада");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);

        WarehouseService warehouseService = new WarehouseService();
        List<Warehouse> stockList = warehouseService.getAllStockLevels();

        StringBuilder sb = new StringBuilder();
        for (Warehouse stock : stockList) {
            sb.append(stock.getComponent().getComponentName())
              .append(" (")
              .append(stock.getComponent().getComponentType())
              .append("): ")
              .append(stock.getAmount())
              .append("\n");
        }

        area.setText(sb.toString());
        add(scrollPane, BorderLayout.CENTER);
    }
}
