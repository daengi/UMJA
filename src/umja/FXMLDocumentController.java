/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umja;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Manuel Simon Klaus Severin
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextArea ta_Output;
    @FXML
    private Button btn_loadUMLFile;
    private Parser parser;

    private File selectedFile;
    private String customPath;
    @FXML
    private Button btn_Convert;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        parser = new Parser(this);
        selectedFile = new File(System.getProperty("user.dir") + "/uml_v2.graphml");
        convertToJava(null);
    }

    @FXML
    private void convertToJava(ActionEvent event) {
        if (selectedFile != null) {
            try {
                parser.parseFile(selectedFile);
            } catch (Exception e) {
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
        if (selectedFile != null) {
            customPath = selectedFile.getParent();
            log("File loaded!");
        } else {
            log("File doesn't exist!");
        }
    }

    public void log(String text) {
        ta_Output.appendText(text + System.lineSeparator());
    }

}
