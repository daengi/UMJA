package umja.test;


import org.junit.jupiter.api.Assertions;
import org.xml.sax.SAXException;
import umja.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        List<UMLClazz> shouldBe = new ArrayList<>();

        UMLClazz one = new UMLClazz("net.htlgrieskirchen.pos2.plf.retrosteam.main",
                "Main",
                UMLClazz.ClassType.CLASS,
                null,
                new ArrayList<String>(),
                new ArrayList<UMLClazzProperty>(Arrays.asList(
                        new UMLClazzProperty(2, "Scanner", "SCANNER"),
                        new UMLClazzProperty(2, "Store", "STORE"),
                        new UMLClazzProperty(2, "User", "user"))),
                new ArrayList<UMLClazzMethod>(Arrays.asList(
                        new UMLClazzMethod(1, "void", "main",
                                new ArrayList<String>(Arrays.asList("String[] args"))))));


        shouldBe.add(one);
        Assertions.assertArrayEquals(shouldBe, returnedClazzes);
    }
}