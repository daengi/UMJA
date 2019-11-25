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

        try {
            List<UMLClazz> returnedClazzes;
            returnedClazzes = parser.parseFile(selectedFile);

            UMLClazz one = new UMLClazz("n0::n0",
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

            );

            UMLClazz two = new UMLClazz("n1::n0",
                    "net.htlgrieskirchen.pos2.plf.retrosteam.store",
                    "GameType",
                    UMLClazz.ClassType.ENUM,
                    null,
                    new ArrayList<String>(),
                    new ArrayList<UMLClazzProperty>(Arrays.asList(
                            new UMLClazzProperty(-1, null, "ACTION, ROLEPLAY,"),
                            new UMLClazzProperty(-1, null, "STRATEGY, SPORT"))),
                    new ArrayList<UMLClazzMethod>()
            );

//            UMLClazz three = new UMLClazz("net.htlgrieskirchen.pos2.plf.retrosteam.store",
//                    "Game",
//                    UMLClazz.ClassType.CLASS,
//                    null,
//                    new ArrayList<>(),
//                    new ArrayList<>(Arrays.asList(
//                            new UMLClazzProperty(2, "String", "name"),
//                            new UMLClazzProperty(2, "String", "price"),
//                            new UMLClazzProperty(2, "String", "published"),
//                            new UMLClazzProperty(2, "GameType", "type"))),
//                    new ArrayList<>(Arrays.asList(
//                            new UMLClazzMethod(1, null, "Game", new ArrayList<>()),
//
//                            new UMLClazzMethod(1, "void", "setName", new ArrayList<>(Arrays.asList("String name"))),
//                            new UMLClazzMethod(1, "void", "setPrice", new ArrayList<>(Arrays.asList("String price"))),
//                            new UMLClazzMethod(1, "void", "setPublished", new ArrayList<>(Arrays.asList("String published"))),
//                            new UMLClazzMethod(1, "void", "setType", new ArrayList<>(Arrays.asList("GameType type"))),
//                            new UMLClazzMethod(1, "String", "toString", new ArrayList<>())
//                    )
//                    ));
//
//            UMLClazz four = new UMLClazz("net.htlgrieskirchen.pos2.plf.retrosteam.store",
//                    "Store",
//                    UMLClazz.ClassType.CLASS,
//                    null,
//                    new ArrayList<String>(),
//                    new ArrayList<UMLClazzProperty>(Arrays.asList(
//                            new UMLClazzProperty(2, "Game[]", "games"))),
//                    new ArrayList<UMLClazzMethod>(Arrays.asList(
//                            new UMLClazzMethod(1, null, "Store", new ArrayList()),
//                            new UMLClazzMethod(1, "Game[]", "getGames", new ArrayList()),
//                            new UMLClazzMethod(1, "void", "addGameToUserWishlist",
//                                    new ArrayList<>(Arrays.asList("User user", "String gameName"))),
//                            new UMLClazzMethod(1, "void", "addGameToUserLibrary",
//                                    new ArrayList<String>(Arrays.asList("User user", "String gameName"))
//                            )
//                    )
//                    ));
//
//            UMLClazz five = new UMLClazz("net.htlgrieskirchen.pos2.plf.retrosteam.user",
//                    "Customer",
//                    UMLClazz.ClassType.CLASS,
//                    null,
//                    new ArrayList<String>(),
//                    new ArrayList<UMLClazzProperty>(Arrays.asList(
//                            new UMLClazzProperty(2, "String", "name"),
//                            new UMLClazzProperty(2, "Game[]", "library"),
//                            new UMLClazzProperty(2, "Game[]", "wishlist"))),
//                    new ArrayList<UMLClazzMethod>(Arrays.asList(
//                            new UMLClazzMethod(1, null, "Customer", new ArrayList<>(Arrays.asList("String name"))),
//
//                            new UMLClazzMethod(1, "Game[]", "getLibrary", new ArrayList()),
//                            new UMLClazzMethod(1, "void", "addToLibrary", new ArrayList<>(Arrays.asList("Game game"))),
//                            new UMLClazzMethod(1, "String", "toString", new ArrayList())
//
//                    )
//                    ));
//            UMLClazz six = new UMLClazz("net.htlgrieskirchen.pos2.plf.retrosteam.user",
//                    "User",
//                    UMLClazz.ClassType.CLASS,
//                    null,
//                    new ArrayList<String>(),
//                    new ArrayList<UMLClazzProperty>(Arrays.asList(
//                            new UMLClazzProperty(1, "int", "CAPACITY"))),
//                    new ArrayList<UMLClazzMethod>(Arrays.asList(
//                            new UMLClazzMethod(1, "", "Customer", new ArrayList<>(Arrays.asList("String name"))),
//
//                            new UMLClazzMethod(1, "Game[]", "getWishlist", new ArrayList()),
//                            new UMLClazzMethod(1, "void", "addToWishlist", new ArrayList<>(Arrays.asList("Game game")))
//
//                    )
//                    ));
//            UMLClazz seven = new UMLClazz("net.htlgrieskirchen.pos2.plf.retrosteam.user",
//                    "Visitor",
//                    UMLClazz.ClassType.CLASS,
//                    null,
//                    new ArrayList<String>(),
//                    new ArrayList<UMLClazzProperty>(Arrays.asList(
//                            new UMLClazzProperty(2, "Game[]", "wishlist"))),
//                    new ArrayList<UMLClazzMethod>(Arrays.asList(
//                            new UMLClazzMethod(1, null, "Visitor", new ArrayList()),
//                            new UMLClazzMethod(1, "String", "toString", new ArrayList())
//                    )
//                    ));

//            List<UMLClazz> expectedClazzes = new ArrayList<>();
//            expectedClazzes.add(one);
//            expectedClazzes.add(two);
//            expectedClazzes.add(three);
//            expectedClazzes.add(four);
//            expectedClazzes.add(five);
//            expectedClazzes.add(six);
//            expectedClazzes.add(seven);


            assert returnedClazzes.contains(one);
            assert returnedClazzes.contains(two);
            //   assert returnedClazzes.contains(three);
            //   assert returnedClazzes.contains(four);
            //assert returnedClazzes.contains(five);
            // assert returnedClazzes.contains(six);
            // assert returnedClazzes.contains(seven);
//            Assertions.assertArrayEquals(expectedClazzes.toArray(), returnedClazzes.toArray());
//        Assertions.assertArrayEquals(shouldBe.toArray(), returnedClazzes.toArray());
        } catch (ParseException | ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}