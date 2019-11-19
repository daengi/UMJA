package umja;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Compiler {

    private FXMLDocumentController fxmlDocumentController;

    public Compiler(FXMLDocumentController fxmlDocumentController) {
        this.fxmlDocumentController = fxmlDocumentController;
    }

    public void compile(String path, List<UMLClazz> umlClazzes) {
        for (UMLClazz clazz : umlClazzes) {
            File file = new File(path + File.separator + clazz.getStrPackage().replace(".", File.separator) + File.separator + clazz.getClazzName() + ".java");
            try {
                Files.createDirectories(Path.of(file.getParent()));

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

                if (!clazz.getProperties().isEmpty()) {
                    for (UMLClazzProperty property : clazz.getProperties()) {
                        if (clazz.getClassType() == UMLClazz.ClassType.ENUM) {
                            builder.append(property.getName());
                        } else {
                            if (property.getModifier() == Modifier.PUBLIC) {
                                builder.append("public ");
                            } else if (property.getModifier() == Modifier.PRIVATE) {
                                builder.append("private ");
                            } else if (property.getModifier() == Modifier.PROTECTED) {
                                builder.append("protected ");
                            }
                            builder.append(property.getDataType());
                            builder.append(" ");
                            builder.append(property.getName());
                            builder.append(";\n");
                        }
                    }
                }

                if (!clazz.getMethods().isEmpty()) {
                    for (UMLClazzMethod method : clazz.getMethods()) {
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
                            builder.append(String.join(", ", method.getParameter()));
                        }
                        builder.append(")");
                        builder.append("{\n");
                        builder.append("\n}\n");
                    }
                }

                //close bracket from class
                builder.append("}");
                try {
                    Files.writeString(file.toPath(), builder.toString());
                } catch (IOException e) {
                    fxmlDocumentController.log(e.getClass().getSimpleName() + ": " + e.getMessage());
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
                fxmlDocumentController.log(clazz.getClazzName() + " failed to create parent dir!");
            }
        }
    }
}
