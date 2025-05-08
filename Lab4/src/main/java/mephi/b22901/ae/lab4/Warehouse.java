package mephi.b22901.ae.lab4;

public class Warehouse {
    private Component component;
    private int amount;

    public Warehouse(Component component, int amount) {
        this.component = component;
        this.amount = amount;
    }


    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "component=" + component.getComponentName() +
                ", amount=" + amount +
                '}';
    }
}
