package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.example.http.HTTPParser;
import org.example.xml.XMLCreator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/org/example/primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
//        System.out.println(App.class.getClassLoader().getResource("src/resource/org/example/primary.fxml").getPath());
        File file = new File("src/main/resources/org/example/primary.fxml");
        String path = file.toURI().toURL().toString();
        System.out.println(path);
        FXMLLoader fxmlLoader = new FXMLLoader(new URL(path));
//        fxmlLoader.set
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        XMLCreator creator = new XMLCreator("test1.xml");

        creator.createXMLFile();

        HTTPParser parser = HTTPParser.getInstance();


        var ref = new Object() {
            String[] strings = new String[5];
        };

        Runnable runnable = () ->
//                ref.strings = parser.parse("https://www.dns-shop.ru/product/e13899d1cb013332/kronstejn-dla-tv-onkron-sn31-cernyj/");
//                Arrays.stream(parser.parse("https://www.dns-shop.ru/product/e13899d1cb013332/kronstejn-dla-tv-onkron-sn31-cernyj/")).forEach(System.out::println);
                creator.writeDataByCode(parser.parse("https://www.dns-shop.ru/product/e13899d1cb013332/kronstejn-dla-tv-onkron-sn31-cernyj/"));
        Thread thread = new Thread(runnable);
        thread.start();

//        Arrays.stream(parser.parse("https://www.dns-shop.ru/product/e13899d1cb013332/kronstejn-dla-tv-onkron-sn31-cernyj/")).forEach(System.out::println);
//        launch(args);


        JFrame a = new JFrame("example");
        JButton button = new JButton("getData");
        JButton buttonCode = new JButton("Code");
        JButton buttonName = new JButton("Name");
        JButton buttonLink = new JButton("Link");
        JButton buttonDate = new JButton("Date");
        JButton buttonPrice = new JButton("Price");

        JTextArea textCode = new JTextArea();
        JTextArea textLink = new JTextArea();
        JTextArea textName = new JTextArea();
        JTextArea textDate = new JTextArea();
        JTextArea textPrice = new JTextArea();

        textCode.setBounds(10, 50, 80, 20);
        textLink.setBounds(100, 50, 80, 20);
        textName.setBounds(190, 50, 80, 20);
        textDate.setBounds(280, 50, 80, 20);
        textPrice.setBounds(370, 50, 80, 20);
        a.add(textCode);
        a.add(textName);
        a.add(textLink);
        a.add(textDate);
        a.add(textPrice);

        button.setBounds(10,100,80,20);
        buttonCode.setBounds(10, 10, 80, 20);
        buttonName.setBounds(100, 10, 80, 20);
        buttonLink.setBounds(190, 10, 80, 20);
        buttonDate.setBounds(280, 10, 80, 20);
        buttonPrice.setBounds(370, 10, 80, 20);
//        b.setAction();
        button.addActionListener(e-> ref.strings = creator.readByCode("1391651"));
        buttonCode.addActionListener(e -> textCode.setText(ref.strings[0]));
        buttonLink.addActionListener(e -> textLink.setText(ref.strings[1]));
        buttonName.addActionListener(e -> textName.setText(ref.strings[2]));
        buttonDate.addActionListener(e -> textDate.setText(ref.strings[4]));
        buttonPrice.addActionListener(e -> textPrice.setText(ref.strings[3]));

        a.add(button);
        a.add(buttonCode);
        a.add(buttonName);
        a.add(buttonLink);
        a.add(buttonDate);
        a.add(buttonPrice);
        a.setSize(800, 800);
        a.setLayout(null);
        a.setVisible(true);
    }

}