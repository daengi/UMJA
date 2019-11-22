package umja;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author Manuel Simon Klaus Severin
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextArea ta_Output;
    @FXML
    private Button btn_loadUMLFile;
    @FXML
    private Button btn_Convert;

    private Parser parser;
    private Compiler compiler;

    private File selectedFile;
    private String customPath;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        parser = new Parser(this);
        compiler = new Compiler(this);
    }

    @FXML
    private void convertToJava(ActionEvent event) {
        if (selectedFile != null) {
            try {
                List<UMLClazz> umlClazzes = parser.parseFile(selectedFile);
                log("Classes found: " + umlClazzes.stream().map(UMLClazz::getClazzName).collect(Collectors.joining(", ")));
                List<File> compiledFiles = compiler.compile(selectedFile.getParent(), umlClazzes);
                log("Compiled Classes: " + compiledFiles.stream().map(File::getName).collect(Collectors.joining(", ")));
            } catch (IOException | ParserConfigurationException | SAXException | ParseException e) {
                log(e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("File doesn't exist");
            alert.setContentText("The selected file doesn't exist any more");
            alert.showAndWait();
        }
    }

    @FXML
    private void openUMLFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        if (customPath == null || customPath.equals("")) {
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        } else fileChooser.setInitialDirectory(new File(customPath));

        fileChooser.setTitle("UML Datei öffnen ...");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("graphml", "*.graphml"));
        selectedFile = fileChooser.showOpenDialog(AnchorPane.getScene().getWindow());
        log("--------------------");
        if (selectedFile != null) {
            customPath = selectedFile.getParent();
            log("File loaded!");
        } else {
            log("File doesn't exist!");
        }
    }

    public void log(String text) {
        if (ta_Output != null) {
            ta_Output.appendText(text + System.lineSeparator());
        }
    }

}
