package org.example;

import org.example.http.HTTPParser;
import org.example.image.ImageConvertor;
import org.example.xml.XMLCreator;

import javax.swing.*;


/**
 * JavaFX App
 */
public class App  {


    public static void main(String[] args) {

        XMLCreator creator = new XMLCreator("test1.xml");

        creator.createXMLFile();

        HTTPParser parser = HTTPParser.getInstance();
        var ref = new Object() {
            String[] strings = new String[5];
        };

        Runnable runnable = () ->
                creator.writeDataByCode(parser.parse("https://www.dns-shop.ru/product/e13899d1cb013332/kronstejn-dla-tv-onkron-sn31-cernyj/"));
        Thread thread = new Thread(runnable);
        thread.start();

        JFrame a = new JFrame("example");
//        JPanel panel = new JPanel();

        JLabel label = new JLabel();
        JButton setData = new JButton("setData");
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
        JTextArea inputLink = new JTextArea();

        label.setIcon(ImageConvertor.resizeImage("image.jpg"));
        label.setBounds(10,200,500,500);
        a.add(label);

//        panel.setBounds(10,50,100,100);
//        a.add(panel);
        inputLink.setBounds(10,100,80,20);
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
        a.add(inputLink);

        setData.setBounds(10,200,80,20);
        button.setBounds(10,100,80,20);
        buttonCode.setBounds(10, 10, 80, 20);
        buttonName.setBounds(100, 10, 80, 20);
        buttonLink.setBounds(190, 10, 80, 20);
        buttonDate.setBounds(280, 10, 80, 20);
        buttonPrice.setBounds(370, 10, 80, 20);

        setData.addActionListener(e-> {
            if (inputLink.getText() !=null){
                creator.writeDataByCode(parser.parse(inputLink.getText()));
            }
        });
        button.addActionListener(e-> ref.strings = creator.readByCode("1391651"));
        buttonCode.addActionListener(e -> textCode.setText(ref.strings[0]));
        buttonLink.addActionListener(e -> textLink.setText(ref.strings[1]));
        buttonName.addActionListener(e -> textName.setText(ref.strings[2]));
        buttonDate.addActionListener(e -> textDate.setText(ref.strings[4]));
        buttonPrice.addActionListener(e -> textPrice.setText(ref.strings[3]));

        a.add(setData);
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