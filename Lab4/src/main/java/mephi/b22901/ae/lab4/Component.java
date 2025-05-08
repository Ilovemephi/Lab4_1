
package mephi.b22901.ae.lab4;

public class Component {
   private int idComponent; 
    private String componentType;
    private String componentName;


    public Component(String componentType, String componentName) {
        this.idComponent = 0;
        this.componentType = componentType;
        this.componentName = componentName;
    }


    public Component(int idComponent, String componentType, String componentName) {
        this.idComponent = idComponent;
        this.componentType = componentType;
        this.componentName = componentName;
    }

    public int getIdComponent() {
        return idComponent;
    }

    public void setIdComponent(int idComponent) {
        this.idComponent = idComponent;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    @Override
    public String toString() {
        return "Component{" +
                "id=" + idComponent +
                ", type='" + componentType + '\'' +
                ", name='" + componentName + '\'' +
                '}';
    }
}
