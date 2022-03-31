package convertFiles;

import java.awt.Desktop;
import java.io.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
    private boolean checker = false;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Converter converter = new Converter();

        final FileChooser fileChooser = new FileChooser();
        final DirectoryChooser directoryChooser = new DirectoryChooser();

        TextArea textArea = new TextArea();
        textArea.setMinHeight(100);

        Button buttonOpenFile = new Button("Выбрать файл");

        Button buttonSaveNewFile = new Button("Указать путь, для сохранения нового файла");

        Button buttonConvert = new Button("Конвертировать файл");

        buttonOpenFile.setOnAction(event -> {
            textArea.clear();
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                try {
                    textArea.setText(getTextFromFile(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            checker = false;
        });

        buttonSaveNewFile.setOnAction(event -> {
            File file = directoryChooser.showDialog(primaryStage);
            try {
                converter.writeFile(file.getAbsolutePath(), "\\newFile1.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        buttonConvert.setOnAction(event -> {
            try {
                if (!checker) {
                    String text = converter.convert(textArea.getText());
                    textArea.clear();
                    textArea.setText(text);
                    checker = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(5);

        root.getChildren().addAll(textArea, buttonOpenFile,buttonConvert,buttonSaveNewFile);

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("TextConverter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getTextFromFile(File file) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            builder.append(reader.readLine() + "\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
