package umja;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final String HTML_REGEX = "<[^>]+>";
    private FXMLDocumentController fxmlDocumentController;

    public Parser(FXMLDocumentController fxmlDocumentController) {
        this.fxmlDocumentController = fxmlDocumentController;
    }

    public List<UMLClazz> parseFile(File file) throws ParseException, ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);

        List<UMLClazz> umlClazzes = new ArrayList<>();
        NodeList nodes = doc.getElementsByTagName("node");
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element nodeElement = (Element) node;
                if (!nodeElement.getAttribute("id").contains(":")) {
                    NodeList umlClassNodes = nodeElement.getElementsByTagName("y:UMLClassNode");

                    String clazzName = null;
                    String strPackage = null;
                    UMLClazz.ClassType classType = UMLClazz.ClassType.CLASS;
                    String inheritsFrom = null;//TODO Use that
                    List<String> interfaces = new ArrayList<>();//TODO Use that
                    List<String> properties = new ArrayList<>();
                    List<String> methods = new ArrayList<>();

                    NodeList nodeLabels = nodeElement.getElementsByTagName("y:NodeLabel");
                    if (nodeLabels.getLength() >= 1) {
                        strPackage = nodeLabels.item(0).getTextContent();
                    }

                    for (int j = 0; j < umlClassNodes.getLength(); j++) {
                        Element umlClassElement = (Element) umlClassNodes.item(j);

                        nodeLabels = umlClassElement.getElementsByTagName("y:NodeLabel");
                        if (nodeLabels.getLength() >= 1) {
                            clazzName = nodeLabels.item(0).getTextContent();
                        } else {
                            throw new ParseException("y:NodeLabel not found", doc.getTextContent().indexOf(umlClassElement.getTextContent()));
                        }
                        NodeList umls = umlClassElement.getElementsByTagName("y:UML");
                        if (umls.getLength() >= 1) {
                            Element uml = (Element) umls.item(0);
                            String constraint = uml.getAttribute("constraint");
                            if (constraint == null) {
                                String stereotype = uml.getAttribute("stereotype");
                                switch (stereotype) {
                                    case "enumeration":
                                        classType = UMLClazz.ClassType.ENUM;
                                        break;
                                    case "interface":
                                        classType = UMLClazz.ClassType.INTERFACE;
                                        break;
                                }
                            } else if (constraint.equals("abstract")) {
                                classType = UMLClazz.ClassType.ABSTRACT;
                            }

                            NodeList attributeLabels = uml.getElementsByTagName("y:AttributeLabel");
                            if (attributeLabels.getLength() >= 1) {
                                Element attributeLabel = (Element) attributeLabels.item(0);
                                String[] clazzProperties = attributeLabel.getTextContent().split("\n");
                                for (String property : clazzProperties) {
                                    properties.add(property.trim().replaceAll(HTML_REGEX, ""));
                                }
                            } else {
                                throw new ParseException("y:AttributeLabel not found", doc.getTextContent().indexOf(uml.getTextContent()));
                            }

                            NodeList methodLabels = uml.getElementsByTagName("y:MethodLabel");
                            if (methodLabels.getLength() >= 1) {
                                Element methodLabel = (Element) methodLabels.item(0);
                                String[] clazzMethods = methodLabel.getTextContent().split("\n");
                                for (String method : clazzMethods) {
                                    methods.add(method.trim().replaceAll(HTML_REGEX, ""));
                                }
                            } else {
                                throw new ParseException("y:MethodLabel not found", doc.getTextContent().indexOf(uml.getTextContent()));
                            }
                        } else {
                            throw new ParseException("y:UML not found", doc.getTextContent().indexOf(umlClassElement.getTextContent()));
                        }
                        umlClazzes.add(new UMLClazz(strPackage, clazzName, classType, inheritsFrom, interfaces, properties, methods));
                    }
                }
            }
        }
        fxmlDocumentController.log("File parsed successfully!");
        return umlClazzes;
    }
}
