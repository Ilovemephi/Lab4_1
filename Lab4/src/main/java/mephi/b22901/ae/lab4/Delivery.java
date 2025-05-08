
package mephi.b22901.ae.lab4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Delivery {
    private int idDelivery;
    private LocalDate deliveryDate;
    private List<DeliveryDetails> details;

    public Delivery(int idDelivery, LocalDate deliveryDate) {
        this.idDelivery = idDelivery;
        this.deliveryDate = deliveryDate;
        this.details = new ArrayList<>();
    }


    public int getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(int idDelivery) {
        this.idDelivery = idDelivery;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<DeliveryDetails> getDetails() {
        return details;
    }

    public void setDetails(List<DeliveryDetails> details) {
        this.details = details;
    }


    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + idDelivery +
                ", date=" + deliveryDate +
                ", items count=" + details.size() +
                '}';
    }
}
