package umja;

import java.io.File;
import java.util.List;

public class Compiler {

    private FXMLDocumentController fxmlDocumentController;

    public Compiler(FXMLDocumentController fxmlDocumentController) {
        this.fxmlDocumentController = fxmlDocumentController;
    }

    public void compile(String path, List<UMLClazz> umlClazzes) {
        for (UMLClazz clazz :
                umlClazzes) {
            File file = new File(path);


            //write package
            StringBuilder builder = new StringBuilder();
            builder.append("package ");
            builder.append(clazz.getStrPackage());
            builder.append(";\n");

            //TODO Imports???????

            //write class notation
            builder.append("public ");
            builder.append(clazz.getClassType().toString().toLowerCase());
            builder.append(" ");
            builder.append(clazz.getClazzName());

            //interfaces and inheritance
            if (clazz.getInheritsFrom() != null) {
                builder.append(" extends ")
                        .append(clazz.getInheritsFrom());
            }
            if (!clazz.getInterfaces().isEmpty()) {
                builder.append(" implements ")
                        .append(String.join(", ", clazz.getInterfaces()))
                        .append(" ");
            }

            builder.append(" {\n");

            //TODO properites

            //TODO Constructor and Methods


            //close bracket from class
            builder.append("}");
        }
    }
}
