package org.example.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import static org.jsoup.nodes.Document.OutputSettings.Syntax.xml;

public class XMLCreator implements Editor {

    String fileName;

    public XMLCreator(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void createXMLFile() {

        File xmlFile = new File(fileName);
        FileWriter writer;
        if (!xmlFile.exists()) {
            try {
                xmlFile.createNewFile();
                writer = new FileWriter(xmlFile);
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?><ProductCatalog></ProductCatalog>");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                writer = new FileWriter(xmlFile);
                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?><ProductCatalog></ProductCatalog>");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String code) {
        DocumentBuilder builder;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(fileName);

            NodeList list = document.getElementsByTagName("Code");

            for (int x = 0; x < list.getLength(); x++) {
                if (list.item(x).getTextContent().equals(code)) {
                    Node node = list.item(x).getParentNode();
                    document.getDocumentElement().removeChild(node);
                }
            }
            write(document, fileName);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void read() {

    }

    @Override
    public void writeData(String... strings) {
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(fileName);

            Node root = document.getDocumentElement();

            Element product = document.createElement("Product");
            Element code = document.createElement("Code");
            Element link = document.createElement("Link");
            Element name = document.createElement("Name");
            Element price = document.createElement("Price");
            Element date = document.createElement("Date");
            Element dateCheck = document.createElement("DateCheck");

            code.setTextContent(strings[0]);
            link.setTextContent(strings[1]);
            name.setTextContent(strings[2]);
            price.setTextContent(strings[3]);
            dateCheck.setTextContent(strings[4]);

            product.appendChild(code);
            product.appendChild(link);
            product.appendChild(name);
            product.appendChild(date);

            date.appendChild(dateCheck);
            date.appendChild(price);

            root.appendChild(product);

            write(document, fileName);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void write(Document document, String fileName) {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(fileName);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }

    }

    @Override
    public void writeDataByCode(String... strings) {
        String code = strings[0];
        DocumentBuilder builder;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(fileName);

            Element dateCheck = document.createElement("DateCheck");
            dateCheck.setTextContent(strings[4]);
            Element price = document.createElement("Price");
            price.setTextContent(strings[3]);

            NodeList nodeList = document.getElementsByTagName("Code");
            NodeList childList = null;
            for (int x = 0; x < nodeList.getLength(); x++) {
                if (nodeList.item(x).getTextContent().equals(code)) {
                    childList = nodeList.item(x).getParentNode().getChildNodes();
                }
            }
            System.out.println(childList.getLength());
            for (int x = 0; x < childList.getLength(); x++) {
                if (childList.item(x).getNodeName().equals("Date")){
                    childList.item(x).appendChild(dateCheck);
                    childList.item(x).appendChild(price);
                }
            }
            write(document,fileName);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
