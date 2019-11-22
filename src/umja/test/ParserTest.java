package umja.test;


import org.xml.sax.SAXException;
import umja.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ParserTest {

    @org.junit.jupiter.api.Test
    void parseFile() {
        File selectedFile = new File(System.getProperty("user.dir") + "/uml_v3.graphml");
        FXMLDocumentController controller = new FXMLDocumentController();
        Parser parser = new Parser(controller);
        List<UMLClazz> returnedClazzes = new ArrayList<>();
        try {
            returnedClazzes = parser.parseFile(selectedFile);

            UMLClazz one = new UMLClazz("net.htlgrieskirchen.pos2.plf.retrosteam.main",
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
            );

            assert returnedClazzes.contains(one);
//        Assertions.assertArrayEquals(shouldBe.toArray(), returnedClazzes.toArray());
        } catch (ParseException | ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}