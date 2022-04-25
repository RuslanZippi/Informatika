package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.xml.XMLCreator;

import java.io.IOException;
import java.util.Date;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {

        String[] strings = new String[5];
        strings[0] = "22";
        strings[1] = "link";
        strings[2] = "testName";
        strings[3] = "TestPrice";
        strings[4] = new Date().toString();
        XMLCreator creator = new XMLCreator("test.xml");
        creator.writeDataByCode(strings);
        creator.delete("2");
        launch();
    }

}