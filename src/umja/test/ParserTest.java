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
import java.util.Collections;
import java.util.List;

class ParserTest {

    public static List<UMLClazz> getUmlClazzes() {
        List<UMLClazz> expectedClazzes = new ArrayList<>();

        expectedClazzes.add(new UMLClazz("n0::n0",
                "net.htlgrieskirchen.pos2.plf.retrosteam.main",
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

        expectedClazzes.add(new UMLClazz("n1::n0",
                "net.htlgrieskirchen.pos2.plf.retrosteam.store",
                "GameType",
                UMLClazz.ClassType.ENUM,
                null,
                new ArrayList<>(),
                new ArrayList<>(Arrays.asList(
                        new UMLClazzProperty(-1, null, "ACTION, ROLEPLAY,"),
                        new UMLClazzProperty(-1, null, "STRATEGY, SPORT"))),
                new ArrayList<>()
        ));

        expectedClazzes.add(new UMLClazz("n1::n1",
                "net.htlgrieskirchen.pos2.plf.retrosteam.store",
                "Game",
                UMLClazz.ClassType.CLASS,
                null,
                new ArrayList<>(),
                new ArrayList<>(Arrays.asList(
                        new UMLClazzProperty(2, "String", "name"),
                        new UMLClazzProperty(2, "String", "price"),
                        new UMLClazzProperty(2, "String", "published"),
                        new UMLClazzProperty(2, "GameType", "type"))),
                new ArrayList<>(Arrays.asList(
                        new UMLClazzMethod(1, null, "Game", Collections.singletonList("")),
                        new UMLClazzMethod(1, "void", "setName", Collections.singletonList("String name")),
                        new UMLClazzMethod(1, "void", "setPrice", Collections.singletonList("String price")),
                        new UMLClazzMethod(1, "void", "setPublished", Collections.singletonList("String published")),
                        new UMLClazzMethod(1, "void", "setType", Collections.singletonList("GameType type")),
                        new UMLClazzMethod(1, "String", "toString", Collections.singletonList("")))
                )
        ));

        expectedClazzes.add(new UMLClazz("n1::n2",
                "net.htlgrieskirchen.pos2.plf.retrosteam.store",
                "Store",
                UMLClazz.ClassType.CLASS,
                null,
                new ArrayList<>(),
                Collections.singletonList(new UMLClazzProperty(2, "Game[]", "games")),
                new ArrayList<>(Arrays.asList(
                        new UMLClazzMethod(1, null, "Store", Collections.singletonList("")),
                        new UMLClazzMethod(1, "Game[]", "getGames", Collections.singletonList("")),
                        new UMLClazzMethod(1, "void", "addGameToUserWishlist",
                                new ArrayList<>(Arrays.asList("User user", "String gameName"))),
                        new UMLClazzMethod(1, "void", "addGameToUserLibrary",
                                new ArrayList<>(Arrays.asList("User user", "String gameName"))))
                )
        ));

        expectedClazzes.add(new UMLClazz("n2::n0",
                "net.htlgrieskirchen.pos2.plf.retrosteam.user",
                "Customer",
                UMLClazz.ClassType.CLASS,
                null,
                Collections.singletonList("n2::n1"),
                new ArrayList<>(Arrays.asList(
                        new UMLClazzProperty(2, "String", "name"),
                        new UMLClazzProperty(2, "Game[]", "library"),
                        new UMLClazzProperty(2, "Game[]", "wishlist"))),
                new ArrayList<>(Arrays.asList(
                        new UMLClazzMethod(1, null, "Customer", Collections.singletonList("String name")),
                        new UMLClazzMethod(1, "Game[]", "getLibrary", Collections.singletonList("")),
                        new UMLClazzMethod(1, "void", "addToLibrary", Collections.singletonList("Game game")),
                        new UMLClazzMethod(1, "String", "toString", Collections.singletonList("")))
                )
        ));

        expectedClazzes.add(new UMLClazz("n2::n1",
                "net.htlgrieskirchen.pos2.plf.retrosteam.user",
                "User",
                UMLClazz.ClassType.INTERFACE,
                null,
                new ArrayList<>(),
                Collections.singletonList(
                        new UMLClazzProperty(1, "int", "CAPACITY")),
                new ArrayList<>(Arrays.asList(
                        new UMLClazzMethod(1, "Game[]", "getWishlist", Collections.singletonList("")),
                        new UMLClazzMethod(1, "void", "addToWishlist", Collections.singletonList("Game game")))
                )
        ));

        expectedClazzes.add(new UMLClazz("n2::n2",
                "net.htlgrieskirchen.pos2.plf.retrosteam.user",
                "Visitor",
                UMLClazz.ClassType.CLASS,
                null,
                Collections.singletonList("n2::n1"),
                Collections.singletonList(
                        new UMLClazzProperty(2, "Game[]", "wishlist")),
                new ArrayList<>(Arrays.asList(
                        new UMLClazzMethod(1, null, "Visitor", Collections.singletonList("")),
                        new UMLClazzMethod(1, "String", "toString", Collections.singletonList("")))
                )
        ));
        return expectedClazzes;
    }

    @org.junit.jupiter.api.Test
    void parseFile() {
        File selectedFile = new File(System.getProperty("user.dir") + "/uml_v3.graphml");
        FXMLDocumentController controller = new FXMLDocumentController();
        Parser parser = new Parser(controller);
        try {
            List<UMLClazz> expectedClazzes = getUmlClazzes();

            List<UMLClazz> returnedClazzes = parser.parseFile(selectedFile);
            Assertions.assertArrayEquals(expectedClazzes.toArray(), returnedClazzes.toArray());
        } catch (ParseException | ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}