package umja.test;

import org.junit.jupiter.api.Test;
import umja.Compiler;
import umja.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class CompilerTest {

    private static final String MAIN_SOURCE = "package net.htlgrieskirchen.pos2.plf.retrosteam.main;\n" +
            "\n" +
            "public class Main {\n" +
            "    private Scanner SCANNER;\n" +
            "    private Store STORE;\n" +
            "    private User user;\n" +
            "\n" +
            "    public void main(String[] args){\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "}";

    @Test
    void compile() {
        FXMLDocumentController controller = new FXMLDocumentController();
        Compiler compiler = new Compiler(controller);

        List<UMLClazz> clazzes = new ArrayList<>();
        clazzes.add(new UMLClazz("net.htlgrieskirchen.pos2.plf.retrosteam.main",
                "Main",
                UMLClazz.ClassType.CLASS,
                null,
                new ArrayList<>(),
                new ArrayList<>(Arrays.asList(
                        new UMLClazzProperty(2, "Scanner", "SCANNER"),
                        new UMLClazzProperty(2, "Store", "STORE"),
                        new UMLClazzProperty(2, "User", "user"))),
                Collections.singletonList(
                        new UMLClazzMethod(1, "void", "main",
                                Collections.singletonList("String[] args"))
                )
        ));
        List<File> compiledFiles = compiler.compile(System.getProperty("user.dir"), clazzes);
        assert compiledFiles.stream().map(file -> {
            try {
                return Files.readString(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }).anyMatch(textContent -> textContent.equals(MAIN_SOURCE));
    }
}