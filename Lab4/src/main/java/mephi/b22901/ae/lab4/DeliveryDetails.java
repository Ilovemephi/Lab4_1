
package mephi.b22901.ae.lab4;


public class DeliveryDetails {
    private int idDetail;
    private Delivery delivery;
    private int idComponent;
    private String componentType;
    private String componentName;
    private int amount;

    public DeliveryDetails(int idDetail, Delivery delivery, int idComponent, String componentType,String componentName, int amount) {
        this.idDetail = idDetail;
        this.delivery = delivery;
        this.idComponent = idComponent;
        this.componentType = componentType;
        this.componentName = componentName;
        this.amount = amount;
    }


    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
    
    public void setComponentType(String componentType){
        this.componentType = componentType;
    }
    public String getComponentType (){
        return componentType;
    }
    
    public void setComponentName(String componentName){
        this.componentName = componentName;
    }
    public String getComponentName (){
        return componentName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public int getIdComponent() {
        return idComponent;
    }

    @Override
    public String toString() {
        return "DeliveryDetails{" +
                "id=" + idDetail +
                ", deliveryId=" + delivery.getIdDelivery() +
                ", type='" + componentType + '\'' +
                ", name='" + componentName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
