
package mephi.b22901.ae.lab4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CreateWandForm extends JFrame {
    private JComboBox<ComponentWrapper> woodComboBox;
    private JComboBox<ComponentWrapper> coreComboBox;

    public CreateWandForm() {
        setTitle("Создание волшебной палочки");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        WarehouseService warehouseService = new WarehouseService();
        List<Warehouse> stockList = warehouseService.getAllStockLevels();


        DefaultComboBoxModel<ComponentWrapper> woodModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<ComponentWrapper> coreModel = new DefaultComboBoxModel<>();

        for (Warehouse warehouse : stockList) {
            Component component = warehouse.getComponent();
            if ("wood".equalsIgnoreCase(component.getComponentType())) {
                woodModel.addElement(new ComponentWrapper(component));
            } else if ("core".equalsIgnoreCase(component.getComponentType())) {
                coreModel.addElement(new ComponentWrapper(component));
            }
        }


        woodComboBox = new JComboBox<>(woodModel);
        coreComboBox = new JComboBox<>(coreModel);


        add(new JLabel("Выберите древесину:"));
        add(woodComboBox);
        add(new JLabel("Выберите сердцевину:"));
        add(coreComboBox);

        JButton createButton = new JButton("Создать палочку");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Component wood = woodComboBox.getItemAt(woodComboBox.getSelectedIndex()).getComponent();
                    Component core = coreComboBox.getItemAt(coreComboBox.getSelectedIndex()).getComponent();


                    WandService wandService = new WandService();
                    wandService.createWand(0, wood, core); 

                    JOptionPane.showMessageDialog(CreateWandForm.this, "Палочка успешно создана!");
                    dispose(); 
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(CreateWandForm.this, "Ошибка: " + ex.getMessage());
                }
            }
        });

        add(createButton);
    }
}

class ComponentWrapper {
    private Component component;

    public ComponentWrapper(Component component) {
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }

    @Override
    public String toString() {
        return component.getComponentName();
    }
}