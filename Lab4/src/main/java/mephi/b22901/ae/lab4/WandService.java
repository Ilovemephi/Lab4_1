
package mephi.b22901.ae.lab4;

import java.util.List;

public class WandService {

    private WandDAO wandDAO = new WandDAO();
    private WarehouseService warehouseService = new WarehouseService();


    public Wand createWand(int idWand, Component wood, Component core) throws Exception {
        if (!warehouseService.isComponentAvailable(wood, 1)) {
            throw new Exception("Нет достаточного количества древесины: " + wood.getComponentName());
        }
        if (!warehouseService.isComponentAvailable(core, 1)) {
            throw new Exception("Нет достаточного количества сердцевины: " + core.getComponentName());
        }

        Wand newWand = new Wand(idWand, wood, core);
        wandDAO.create(newWand);


        warehouseService.decreaseStock(wood, 1);
        warehouseService.decreaseStock(core, 1);

        return newWand;
    }


    public void sellWand(int wandId, String buyerName) {
        Wand wand = wandDAO.getById(wandId);
        if (wand == null) {
            System.out.println(" Палочка с ID " + wandId + " не найдена.");
            return;
        }

        if ("продано".equals(wand.getStatus())) {
            System.out.println(" Эта палочка уже продана.");
            return;
        }

        wand.assignOwner(buyerName);
        wand.setStatus("продано");
        wandDAO.update(wand);
    }


    public List<Wand> getAllWands() {
        return wandDAO.getAll();
    }


    public List<Wand> getAvailableWands() {
        return wandDAO.getAvailableWands();
    }


    public Wand getWandById(int id) {
        return wandDAO.getById(id);
    }
}
