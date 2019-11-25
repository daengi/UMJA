package umja.test;

import org.junit.jupiter.api.Test;
import umja.Compiler;
import umja.FXMLDocumentController;
import umja.UMLClazz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

class CompilerTest {
    private static final String[] SOURCES = new String[]{
            "package net.htlgrieskirchen.pos2.plf.retrosteam.main;\n" +
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
                    "}",

            "package net.htlgrieskirchen.pos2.plf.retrosteam.store;\n" +
                    "\n" +
                    "public enum GameType {\n" +
                    "    ACTION, ROLEPLAY,STRATEGY, SPORT\n" +
                    "}",

            "package net.htlgrieskirchen.pos2.plf.retrosteam.store;\n" +
                    "\n" +
                    "public class Game {\n" +
                    "    private String name;\n" +
                    "    private String price;\n" +
                    "    private String published;\n" +
                    "    private GameType type;\n" +
                    "\n" +
                    "    public Game(){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    public void setName(String name){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    public void setPrice(String price){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    public void setPublished(String published){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    public void setType(GameType type){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    public String toString(){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "}",

            "package net.htlgrieskirchen.pos2.plf.retrosteam.store;\n" +
                    "\n" +
                    "public class Store {\n" +
                    "    private Game[] games;\n" +
                    "\n" +
                    "    public Store(){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    public Game[] getGames(){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    public void addGameToUserWishlist(User user, String gameName){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    public void addGameToUserLibrary(User user, String gameName){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "}",

            "package net.htlgrieskirchen.pos2.plf.retrosteam.user;\n" +
                    "\n" +
                    "public class Customer implements User {\n" +
                    "    private String name;\n" +
                    "    private Game[] library;\n" +
                    "    private Game[] wishlist;\n" +
                    "\n" +
                    "    public Customer(String name){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    public Game[] getLibrary(){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    public void addToLibrary(Game game){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    public String toString(){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "}",

            "package net.htlgrieskirchen.pos2.plf.retrosteam.user;\n" +
                    "\n" +
                    "public interface User {\n" +
                    "    public int CAPACITY;\n" +
                    "\n" +
                    "    public Game[] getWishlist(){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    public void addToWishlist(Game game){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "}",

            "package net.htlgrieskirchen.pos2.plf.retrosteam.user;\n" +
                    "\n" +
                    "public class Visitor implements User {\n" +
                    "    private Game[] wishlist;\n" +
                    "\n" +
                    "    public Visitor(){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "    public String toString(){\n" +
                    "\n" +
                    "    }\n" +
                    "\n" +
                    "}"
    };


    @Test
    void compile() {
        FXMLDocumentController controller = new FXMLDocumentController();
        Compiler compiler = new Compiler(controller);

        List<UMLClazz> expectedClazzes = ParserTest.getUmlClazzes();

        List<String> list = Arrays.asList(SOURCES);

        List<File> compiledFiles = compiler.compile(System.getProperty("user.dir") + File.separator + "test", expectedClazzes);
        assert compiledFiles.stream().map(file -> {
            try {
                return Files.readString(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }).allMatch(list::contains);
    }
}