package org.example.xml;

import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;

public interface Editor {

     void createXMLFile();
     void delete(String code);
     void read();

     void write(Document document, String fileName);

     void writeData(String ... strings);

     void writeDataByCode(String... strings);

}
