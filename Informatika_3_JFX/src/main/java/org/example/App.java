package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.http.HTTPParser;
import org.example.xml.XMLCreator;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

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

        String[] strings = new String[5];
        strings[0] = "22";
        strings[1] = "link";
        strings[2] = "testName";
        strings[3] = "TestPrice";
        strings[4] = new Date().toString();
        XMLCreator creator = new XMLCreator("test.xml");
        creator.writeDataByCode(strings);
        creator.delete("2");

//        HTTPParser parser = new HTTPParser("https://www.dns-shop.ru/product/e13899d1cb013332/kronstejn-dla-tv-onkron-sn31-cernyj/");
        File file = new File("src\\main\\resources\\org\\example\\chromedriver.exe");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(file.toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("ok");
            try {
                System.out.println(file.toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
//        parser.parse();
        launch(args);
    }

}