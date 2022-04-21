import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import java.io.IOException;

public class Main {
    static String filePath = "src\\resource\\file.xml";

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(filePath);
        Node node = document.getDocumentElement();
        NodeList nodeList = node.getChildNodes();
        for (int x = 0; x < nodeList.getLength(); x++) {
            if (nodeList.item(x).getNodeName().equals("to")){
                System.out.println(nodeList.item(x).getTextContent());
            }
        }
    }
}
