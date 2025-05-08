
package mephi.b22901.ae.lab4;

public class Wand {
    private int idWand;
    private String ownerName;
    private Component wood;
    private Component core;
    private String status;

    public Wand(int idWand, Component wood, Component core) {
        this.idWand = idWand;
        this.wood = wood;
        this.core = core;
        this.status = "в наличии";
    }


    public int getIdWand() {
        return idWand;
    }

    public void setIdWand(int idWand) {
        this.idWand = idWand;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Component getWood() {
        return wood;
    }

    public void setWood(Component wood) {
        this.wood = wood;
    }

    public Component getCore() {
        return core;
    }

    public void setCore(Component core) {
        this.core = core;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void assignOwner(String ownerName) {
        this.ownerName = ownerName;
        this.status = "продано";
    }


    public void sell() {
        this.status = "продано";
    }

    @Override
    public String toString() {
        return "Wand{" +
                "id=" + idWand +
                ", владелец='" + ownerName + '\'' +
                ", древесина='" + wood.getComponentName() + '\'' +
                ", сердцевина='" + core.getComponentName() + '\'' +
                ", статус='" + status + '\'' +
                '}';
    }
}
