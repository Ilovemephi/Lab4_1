
package mephi.b22901.ae.lab4;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class WarehouseView extends JFrame {


    private Map<String, String> typeDisplayMapping;

    public WarehouseView() {
        setTitle("Состояние склада");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        initTypeMapping();

        JTextArea area = new JTextArea();
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);

        WarehouseService warehouseService = new WarehouseService();
        List<Warehouse> stockList = warehouseService.getAllStockLevels();

        StringBuilder sb = new StringBuilder();

        if (stockList.isEmpty()) {
            sb.append("На складе пока нет компонентов.");
        } else {
            for (Warehouse stock : stockList) {
                Component component = stock.getComponent();
                String displayType = typeDisplayMapping.getOrDefault(component.getComponentType(), component.getComponentType());
                sb.append(displayType)
                  .append(": ")
                  .append(component.getComponentName())
                  .append(" — ")
                  .append(stock.getAmount())
                  .append("\n");
            }
        }

        area.setText(sb.toString());
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initTypeMapping() {
        typeDisplayMapping = new HashMap<>();
        typeDisplayMapping.put("wood", "древесина");
        typeDisplayMapping.put("core", "сердцевина");
    }
}