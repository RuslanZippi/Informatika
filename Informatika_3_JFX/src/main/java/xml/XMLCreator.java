package xml;

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
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

public class XMLCreator {


    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError, URISyntaxException {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);

            File file = new File(XMLCreator.class.getClassLoader().getResource("dataProducts.xml").getPath());
            System.out.println(file.exists());
            FileOutputStream fos = new FileOutputStream(new File(XMLCreator.class.getClassLoader().getResource("dataProducts.xml").getPath()));
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private boolean ifExists(Document document, String productCode) {
        NodeList nodeList = document.getElementsByTagName("Code");
        for (int x = 0; x < nodeList.getLength(); x++) {
            if (nodeList.item(x).getTextContent().equals(productCode)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, URISyntaxException {

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            System.out.println(XMLCreator.class.getClassLoader().getResource("dataProducts.xml").getPath());
            Document document = builder.parse(XMLCreator.class.getClassLoader().getResource("dataProducts.xml").getPath());

            Node root = document.getDocumentElement();

            Element product = document.createElement("Product");

            Element link = document.createElement("Link");
            link.setTextContent("TestLink");

            Element code = document.createElement("Code");
            code.setTextContent("TestCode");

            Element name = document.createElement("Name");
            name.setTextContent("TestName");

            Element price = document.createElement("Price");
            price.setTextContent("TestPrice");

            Element date = document.createElement("Date");


            Element dateCheck = document.createElement("DateCheck");
            dateCheck.setTextContent(new Date().toString());


            date.appendChild(dateCheck);
            date.appendChild(price);

            product.appendChild(code);
            product.appendChild(name);
            product.appendChild(date);
            product.appendChild(link);

            root.appendChild(product);

            writeDocument(document);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
