/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umja;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * @author User
 */
public class Parser {
    private FXMLDocumentController fxmlDocumentController;

    public Parser(FXMLDocumentController fxmlDocumentController) {
        this.fxmlDocumentController = fxmlDocumentController;
    }

    public void parseFile(File file) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);

        NodeList umlClassNodes = doc.getElementsByTagName("y:UMLClassNode");
        for (int i = 0; i < umlClassNodes.getLength(); i++) {
            Node umlClassNode = umlClassNodes.item(i);
            if (umlClassNode.getNodeType() == Node.ELEMENT_NODE) {
                Element umlClassElement = (Element) umlClassNode;

                NodeList nodeLabels = umlClassElement.getElementsByTagName("y:NodeLabel");
                if (nodeLabels.getLength() >= 1) {
                    Node nodeLabel = nodeLabels.item(0);
                    System.out.println("ClassName: " + nodeLabel.getTextContent());
                } else {
                    throw new Exception("y:NodeLabel not found");
                }
                NodeList umls = umlClassElement.getElementsByTagName("y:UML");
                if (umls.getLength() >= 1) {
                    Element uml = (Element) umls.item(0);

                    NodeList attributeLabels = uml.getElementsByTagName("y:AttributeLabel");
                    if (attributeLabels.getLength() >= 1) {
                        Element attributeLabel = (Element) attributeLabels.item(0);
                        String properties = attributeLabel.getTextContent();
                        System.out.println("Properties: " + properties);
                    } else {
                        throw new Exception("y:AttributeLabel not found");
                    }

                    NodeList methodLabels = uml.getElementsByTagName("y:MethodLabel");
                    if (methodLabels.getLength() >= 1) {
                        Element methodLabel = (Element) methodLabels.item(0);
                        String methods = methodLabel.getTextContent();
                        System.out.println("Methods: " + methods);
                    } else {
                        throw new Exception("y:MethodLabel not found");
                    }
                } else {
                    throw new Exception("y:UML not found");
                }
            }
        }
        fxmlDocumentController.log("File parsed successfully!");
    }
}
