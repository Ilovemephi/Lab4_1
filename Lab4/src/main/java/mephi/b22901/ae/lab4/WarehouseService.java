
package mephi.b22901.ae.lab4;

import java.util.List;

public class WarehouseService {

    private WarehouseDAO warehouseDAO = new WarehouseDAO();
    private ComponentDAO componentDAO = new ComponentDAO();


    public boolean isComponentAvailable(Component component, int requiredAmount) {
        Warehouse record = warehouseDAO.getById(component.getIdComponent());
        return record != null && record.getAmount() >= requiredAmount;
    }


    public void increaseStock(Component component, int amountToAdd) {
        Warehouse record = warehouseDAO.getById(component.getIdComponent());

        if (record == null) {
            warehouseDAO.create(new Warehouse(component, amountToAdd));
        } else {
            int newAmount = record.getAmount() + amountToAdd;
            record.setAmount(newAmount);
            warehouseDAO.update(record);
        }
    }

    public void decreaseStock(Component component, int amountToUse) throws Exception {
        Warehouse record = warehouseDAO.getById(component.getIdComponent());

        if (record == null || record.getAmount() < amountToUse) {
            throw new Exception("Недостаточно " + component.getComponentName() + " на складе.");
        }

        record.setAmount(record.getAmount() - amountToUse);
        warehouseDAO.update(record);
    }


    public void updateStock(Component component, int newAmount) {
        warehouseDAO.updateStock(component, newAmount);
    }


    public int getCurrentStock(Component component) {
        Warehouse record = warehouseDAO.getById(component.getIdComponent());
        return record != null ? record.getAmount() : 0;
    }


    public List<Warehouse> getAllStockLevels() {
        return warehouseDAO.getAll();
    }


    public void removeComponentFromStock(int componentId) {
        warehouseDAO.delete(componentId);
    }
}
