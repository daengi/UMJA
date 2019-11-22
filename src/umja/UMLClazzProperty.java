package umja;

import java.util.Objects;

public class UMLClazzProperty {

    private int modifier;
    private String dataType;
    private String name;

    public UMLClazzProperty(int modifier, String dataType, String name) {
        this.modifier = modifier;
        this.dataType = dataType;
        this.name = name;

    }

    /**
     * @return modifer, in case of an enum it's -1
     */
    public int getModifier() {
        return modifier;
    }

    public String getDataType() {
        return dataType;
    }

    /**
     * @return name of the variable or if it's an enum it returns the whole line of enums
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UMLClazzProperty{" +
                "modifier=" + modifier +
                ", dataType='" + dataType + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UMLClazzProperty that = (UMLClazzProperty) o;
        return modifier == that.modifier &&
                Objects.equals(dataType, that.dataType) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modifier, dataType, name);
    }
}
