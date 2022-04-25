module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires org.jsoup;

    opens org.example.controllers to javafx.fxml;
    exports org.example;
}
