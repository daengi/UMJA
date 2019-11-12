package umja;

import java.io.File;
import java.lang.reflect.Modifier;
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
            //builder.append(clazz.getClassType().toString().toLowerCase());
            //builder.append(clazz.
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
            //if(!clazz.getProperties().isEmpty()){
            //    builder.append(String.join(";\n ", clazz.getProperties()));

            //}

            //TODO Constructor and Methods
            if (!clazz.getMethods().isEmpty()) {
                for (UMLClazzMethod method :
                        clazz.getMethods()) {

                    if (method.getModifier() == Modifier.PUBLIC) {
                        builder.append("public ");
                    } else if (method.getModifier() == Modifier.PRIVATE) {
                        builder.append("private ");
                    } else if (method.getModifier() == Modifier.PROTECTED) {
                        builder.append("protected ");
                    }
                    if (method.getReturnValue() != null) {
                        builder.append(method.getReturnValue());
                        builder.append(" ");
                    }
                    builder.append(method.getName());
                    builder.append("(");
                    if (!method.getParameter().isEmpty()) {
                        builder.append(String.join(", ", clazz.getProperties()));
                    }
                    builder.append(")");
                    builder.append("{\n");
                    builder.append("\n}\n");
                }
            }

            //close bracket from class
            builder.append("}");


        }
    }
}
