package umja;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UMLClazzMethod that = (UMLClazzMethod) o;
        return modifier == that.modifier &&
                Objects.equals(returnValue, that.returnValue) &&
                Objects.equals(name, that.name) &&
                Objects.equals(parameter, that.parameter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modifier, returnValue, name, parameter);
    }
}
