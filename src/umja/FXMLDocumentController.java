/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umja;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.*;
import java.net.URL;
import java.util.Optional;
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
    @FXML
    private Button btn_Settings;


    private File selectedFile;
    private String customPath = "";
    @FXML
    private Button btn_Convert;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void readFile() {
        //ToDo:
    }

    @FXML
    private void ConvertToJava(ActionEvent event) {
    }

    @FXML
    private void openUMLFile(ActionEvent event) {
        loadPath();
        FileChooser fileChooser = new FileChooser();
        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);

        if (customPath.equals("")) {
            if (!userDirectory.canRead())
                userDirectory = new File("c:/Users/" + System.getProperty("user.name") + "/Documents");
            fileChooser.setInitialDirectory(userDirectory);
        } else fileChooser.setInitialDirectory(new File(customPath));


        fileChooser.setTitle("UML Datei öffnen ...");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("graphml", "*.graphml"));
        selectedFile = fileChooser.showOpenDialog(AnchorPane.getScene().getWindow());
        if (selectedFile != null) readFile();
    }

    private void loadPath() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("path.csv"));
            customPath = reader.readLine();
            reader.close();
        } catch (IOException ex) {
            ta_Output.appendText("Could not load the old path");
        }
    }

    @FXML
    private void openSettingsMenu(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog(customPath);
        dialog.setTitle("Settings");
        dialog.setHeaderText("Settings for Path");
        dialog.setContentText("Please enter your standard Path:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(path -> customPath = path);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("path.csv", false));
            writer.write(customPath);
            writer.close();
        } catch (IOException ex) {
            ta_Output.appendText("Could not save the new path.");
        }
    }
}
