package org.example.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import org.example.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("/org/example/secondary");
    }
}
