package org.example;

import org.example.gui.GUIInterface;
import org.example.http.HTTPParser;
import org.example.image.ImageConvertor;
import org.example.xml.XMLCreator;

import javax.swing.*;
import java.io.IOException;


/**
 * JavaFX App
 */
public class App {


    public static void main(String[] args) throws IOException {

        GUIInterface guiInterface = new GUIInterface();
        guiInterface.createInterface();
    }

}