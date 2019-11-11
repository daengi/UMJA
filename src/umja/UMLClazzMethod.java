package umja;

import java.util.List;

public class UMLClazzMethod {

    private int modifier;
    private String returnValue;
    private String name;
    private List<String> parameter;

    public UMLClazzMethod(int modifier, String returnValue, String name, List<String> parameter) {
        this.modifier = modifier;
        this.returnValue = returnValue;
        this.name = name;
        this.parameter = parameter;
    }

    public int getModifier() {
        return modifier;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public String getName() {
        return name;
    }

    public List<String> getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        return "UMLClazzMethod{" +
                "modifier=" + modifier +
                ", returnValue='" + returnValue + '\'' +
                ", name='" + name + '\'' +
                ", parameter=" + parameter +
                '}';
    }
}
