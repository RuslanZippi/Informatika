package org.example.gui;

import org.example.http.HTTPParser;
import org.example.image.ImageConvertor;
import org.example.xml.XMLCreator;

import javax.swing.*;

public class GUIInterface {
    private final XMLCreator creator = new XMLCreator("DNS.xml");
    private final HTTPParser parser = HTTPParser.getInstance();
    private boolean checker = false;

    public void createInterface() {

        creator.createXMLFile();
        var ref = new Object() {
            String[] strings = new String[5];
        };

        /**
         * Создание элементов
         */
        JFrame mainFrame = new JFrame("example");
        JComboBox<String> stringJComboBox = new JComboBox<>();

        JButton refreshButton = new JButton("Обновить данные");
        JButton addButton = new JButton("Добавить продукт");
        JButton lookingButton = new JButton("Просмотреть цену выбранного товара");
        JTextArea textAreaData = new JTextArea("TestText");

        JDialog dataDialog = new JDialog(mainFrame, true);
        JTextArea inputLinkTextInDialog = new JTextArea();
        JButton uploadLinkButton = new JButton("Загрузить");
        /**
         */


        /**
         * Установка параметров у элементов
         */
        inputLinkTextInDialog.setBounds(10, 10, 470, 30);
        uploadLinkButton.setBounds(200, 100, 100, 30);

        stringJComboBox.setBounds(10, 10, 500, 30);
        addButton.setBounds(550, 10, 200, 30);
        refreshButton.setBounds(550, 60, 200, 30);
        textAreaData.setBounds(10, 300, 600, 300);
        lookingButton.setBounds(80, 100, 300, 30);
        /**
         */

        creator.getProductNameList().forEach(stringJComboBox::addItem);
        /**
         * Работа с Dialog
         */
        dataDialog.setSize(500, 200);
        dataDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        dataDialog.setLayout(null);
        /**
         */

        /**
         * Добавление элементов в Dialog
         */
        dataDialog.add(inputLinkTextInDialog);
        dataDialog.add(uploadLinkButton);
        /**
         */


        /**
         * Добавление элементов в гланвый Frame
         */
        mainFrame.add(stringJComboBox);
        mainFrame.add(refreshButton);
        mainFrame.add(addButton);
        mainFrame.add(textAreaData);
        mainFrame.add(lookingButton);
        /**
         */


        /**
         * Обработчики кнопок
         */
        addButton.addActionListener(e ->
                dataDialog.setVisible(true));

        uploadLinkButton.addActionListener(e -> {
            String link = inputLinkTextInDialog.getText();
            creator.writeDataByCode(parser.parse(link));
            dataDialog.setVisible(false);
        });

        refreshButton.addActionListener(e -> {
            creator.getProductNameList().forEach(stringJComboBox::addItem);
        });

        lookingButton.addActionListener(e->{
            textAreaData.setText("");
            creator.getPriceByName(stringJComboBox.getSelectedItem().toString()).forEach(x-> textAreaData.append(x + "\n"));
        });
        /**
         */

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 800);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);


    }
}
