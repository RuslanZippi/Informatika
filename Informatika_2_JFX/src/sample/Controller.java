package sample;

import com.sun.org.omg.CORBA.Initializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class Controller implements Initializable {
    @FXML
    private TextArea textIn;
    @FXML
    private TextArea textOut;
    @FXML
    private ComboBox boxIn;
    @FXML
    private ComboBox boxOut;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Button button;
    @FXML
    private TextArea textPatternIn;
    @FXML
    private TextArea textPatternOut;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textPatternIn.setText(fillPatternIn(2));
        textPatternOut.setText(fillPatternOut(2, 2));

        boxOut.getItems().addAll("2-система",
                "3-система",
                "4-система",
                "5-система",
                "6-система",
                "7-система",
                "8-система",
                "9-система",
                "10-система",
                "11-система",
                "12-система",
                "13-система",
                "14-система",
                "15-система",
                "16-система");
        boxIn.getItems().addAll("2-система",
                "3-система",
                "4-система",
                "5-система",
                "6-система",
                "7-система",
                "8-система",
                "9-система",
                "10-система",
                "11-система",
                "12-система",
                "13-система",
                "14-система",
                "15-система",
                "16-система");
        boxIn.getSelectionModel().select(0);
        boxOut.getSelectionModel().select(0);

    }

    public void buttonClick() {
        convertValue();
        textOut.setText(String.valueOf(convert(textIn.getText())));
    }

    public void buttonClickCheck() {
        if (checkBox.isSelected()) {
            textOut.setText(String.valueOf(convert(textIn.getText())));
        }
    }

    private int convert(String s) {
        Pattern p = Pattern.compile("\\D");

        int x;
        System.out.println(checkTwoSystem(s));
        if (s.equals("") || p.matcher(s).find()) {
            x = 0;
        } else {
            x = Integer.parseInt(s);
        }
        return x * 2;
    }

    private boolean checkTwoSystem(String s) {
        Pattern pp = Pattern.compile("\\D");
        Pattern p = Pattern.compile("[^1,0]");
        if (!pp.matcher(s).find()) {
            if (!p.matcher(s).find()) {
                return true;
            } else {
                getAlert();
                return false;
            }
        } else {
            getAlert();
            return false;
        }
    }

    private void getAlert() {
        String error;
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка ввода");
        switch (boxIn.getSelectionModel().getSelectedItem().toString()) {

            case "2-система":
                error = ErrorEnum.TWO.getUrl();
                break;
            case "3-система":
                error = ErrorEnum.THREE.getUrl();
                break;
            case "4-система":
                error = ErrorEnum.FOUR.getUrl();
                break;
            case "5-система":
                error = ErrorEnum.FIFE.getUrl();
                break;
            case "6-система":
                error = ErrorEnum.SIX.getUrl();
                break;
            case "7-система":
                error = ErrorEnum.SEVEN.getUrl();
                break;
            case "8-система":
                error = ErrorEnum.EIGHT.getUrl();
                break;
            case "9-система":
                error = ErrorEnum.NINE.getUrl();
                break;
            case "10-система":
                error = ErrorEnum.TEN.getUrl();
                break;
            case "11-система":
                error = ErrorEnum.ELEVEN.getUrl();
                break;
            case "12-система":
                error = ErrorEnum.TWELVE.getUrl();
                break;
            case "13-система":
                error = ErrorEnum.THIRTEEN.getUrl();
                break;
            case "14-система":
                error = ErrorEnum.FOURTEEN.getUrl();
                break;
            case "15-система":
                error = ErrorEnum.FIFTEEN.getUrl();
                break;
            case "16-система":
                error = ErrorEnum.SIXTEEN.getUrl();
                break;
            default:
                error = "";
        }
        alert.setContentText("Разрешено использовать только цифры, в диапозоне: " + error);
        alert.showAndWait();
    }

    public void clickCheckBox(ActionEvent actionEvent) {
        if (checkBox.isSelected()) {
            button.setDisable(true);
        } else {
            button.setDisable(false);
        }
    }

    private String fillPatternIn(int outSystem) {
        String text = "";
        text = "Таблица конвертации из 10-системы в 2-систему:\n" +
                "1 = " + Integer.toUnsignedString(1, outSystem) + "\t\t\t 9 = " + Integer.toUnsignedString(9, outSystem) + "\n" +
                "2 = " + Integer.toUnsignedString(2, outSystem) + "\t\t\t 10 = " + Integer.toUnsignedString(10, outSystem) + "\n" +
                "3 = " + Integer.toUnsignedString(3, outSystem) + "\t\t\t 11 = " + Integer.toUnsignedString(11, outSystem) + "\n" +
                "4 = " + Integer.toUnsignedString(4, outSystem) + "\t\t\t 12 = " + Integer.toUnsignedString(12, outSystem) + "\n" +
                "5 = " + Integer.toUnsignedString(5, outSystem) + "\t\t\t 13 = " + Integer.toUnsignedString(13, outSystem) + "\n" +
                "6 = " + Integer.toUnsignedString(6, outSystem) + "\t\t\t 14 = " + Integer.toUnsignedString(14, outSystem) + "\n" +
                "7 = " + Integer.toUnsignedString(7, outSystem) + "\t\t\t 15 = " + Integer.toUnsignedString(15, outSystem) + "\n" +
                "8 = " + Integer.toUnsignedString(8, outSystem) + "\t\t\t 16 = " + Integer.toUnsignedString(16, outSystem) + "\n";
//        switch (outSystem) {
//            case 2:
//                text = "Таблица конвертации из 10-системы в 2-систему:\n" +
//                        "1 = " + Integer.toUnsignedString(1, 2) + "\t\t\t 9 = " + Integer.toUnsignedString(9, 2) + "\n" +
//                        "2 = " + Integer.toUnsignedString(2, 2) + "\t\t\t 10 = " + Integer.toUnsignedString(10, 2) + "\n" +
//                        "3 = " + Integer.toUnsignedString(3, 2) + "\t\t\t 11 = " + Integer.toUnsignedString(11, 2) + "\n" +
//                        "4 = " + Integer.toUnsignedString(4, 2) + "\t\t\t 12 = " + Integer.toUnsignedString(12, 2) + "\n" +
//                        "5 = " + Integer.toUnsignedString(5, 2) + "\t\t\t 13 = " + Integer.toUnsignedString(13, 2) + "\n" +
//                        "6 = " + Integer.toUnsignedString(6, 2) + "\t\t\t 14 = " + Integer.toUnsignedString(14, 2) + "\n" +
//                        "7 = " + Integer.toUnsignedString(7, 2) + "\t\t\t 15 = " + Integer.toUnsignedString(15, 2) + "\n" +
//                        "8 = " + Integer.toUnsignedString(8, 2) + "\t\t\t 16 = " + Integer.toUnsignedString(16, 2) + "\n";
//                break;
//            case 3:
//                text = "Таблица конвертации из 10-системы в 3-систему:\n" +
//                        "1 = " + Integer.toUnsignedString(1, 3) + "\t\t\t 9 = " + Integer.toUnsignedString(9, 3) + "\n" +
//                        "2 = " + Integer.toUnsignedString(2, 3) + "\t\t\t 10 = " + Integer.toUnsignedString(10, 3) + "\n" +
//                        "3 = " + Integer.toUnsignedString(3, 3) + "\t\t\t 11 = " + Integer.toUnsignedString(11, 3) + "\n" +
//                        "4 = " + Integer.toUnsignedString(4, 3) + "\t\t\t 12 = " + Integer.toUnsignedString(12, 3) + "\n" +
//                        "5 = " + Integer.toUnsignedString(5, 3) + "\t\t\t 13 = " + Integer.toUnsignedString(13, 3) + "\n" +
//                        "6 = " + Integer.toUnsignedString(6, 3) + "\t\t\t 14 = " + Integer.toUnsignedString(14, 3) + "\n" +
//                        "7 = " + Integer.toUnsignedString(7, 3) + "\t\t\t 15 = " + Integer.toUnsignedString(15, 3) + "\n" +
//                        "8 = " + Integer.toUnsignedString(8, 3) + "\t\t\t 16 = " + Integer.toUnsignedString(16, 3) + "\n";
//                break;
//            case 4:
//                text = "Таблица конвертации из 10-системы в 4-систему:\n" +
//                        "1 = " + Integer.toUnsignedString(1, 4) + "\t\t\t 9 = " + Integer.toUnsignedString(9, 4) + "\n" +
//                        "2 = " + Integer.toUnsignedString(2, 4) + "\t\t\t 10 = " + Integer.toUnsignedString(10, 4) + "\n" +
//                        "3 = " + Integer.toUnsignedString(3, 4) + "\t\t\t 11 = " + Integer.toUnsignedString(11, 4) + "\n" +
//                        "4 = " + Integer.toUnsignedString(4, 4) + "\t\t\t 12 = " + Integer.toUnsignedString(12, 4) + "\n" +
//                        "5 = " + Integer.toUnsignedString(5, 4) + "\t\t\t 13 = " + Integer.toUnsignedString(13, 4) + "\n" +
//                        "6 = " + Integer.toUnsignedString(6, 4) + "\t\t\t 14 = " + Integer.toUnsignedString(14, 4) + "\n" +
//                        "7 = " + Integer.toUnsignedString(7, 4) + "\t\t\t 15 = " + Integer.toUnsignedString(15, 4) + "\n" +
//                        "8 = " + Integer.toUnsignedString(8, 4) + "\t\t\t 16 = " + Integer.toUnsignedString(16, 4) + "\n";
//                break;
//            case 5:
//                text = "Таблица конвертации из 10-системы в 5-систему:\n" +
//                        "1 = " + Integer.toUnsignedString(1, 5) + "\t\t\t 9 = " + Integer.toUnsignedString(9, 5) + "\n" +
//                        "2 = " + Integer.toUnsignedString(2, 5) + "\t\t\t 10 = " + Integer.toUnsignedString(10, 5) + "\n" +
//                        "3 = " + Integer.toUnsignedString(3, 5) + "\t\t\t 11 = " + Integer.toUnsignedString(11, 5) + "\n" +
//                        "4 = " + Integer.toUnsignedString(4, 5) + "\t\t\t 12 = " + Integer.toUnsignedString(12, 5) + "\n" +
//                        "5 = " + Integer.toUnsignedString(5, 5) + "\t\t\t 13 = " + Integer.toUnsignedString(13, 5) + "\n" +
//                        "6 = " + Integer.toUnsignedString(6, 5) + "\t\t\t 14 = " + Integer.toUnsignedString(14, 5) + "\n" +
//                        "7 = " + Integer.toUnsignedString(7, 5) + "\t\t\t 15 = " + Integer.toUnsignedString(15, 5) + "\n" +
//                        "8 = " + Integer.toUnsignedString(8, 5) + "\t\t\t 16 = " + Integer.toUnsignedString(16, 5) + "\n";
//                break;
//            case 6:
//                text = "Таблица конвертации из 10-системы в 6-систему:\n" +
//                        "1 = " + Integer.toUnsignedString(1, 6) + "\t\t\t 9 = " + Integer.toUnsignedString(9, 6) + "\n" +
//                        "2 = " + Integer.toUnsignedString(2, 6) + "\t\t\t 10 = " + Integer.toUnsignedString(10, 6) + "\n" +
//                        "3 = " + Integer.toUnsignedString(3, 6) + "\t\t\t 11 = " + Integer.toUnsignedString(11, 6) + "\n" +
//                        "4 = " + Integer.toUnsignedString(4, 6) + "\t\t\t 12 = " + Integer.toUnsignedString(12, 6) + "\n" +
//                        "5 = " + Integer.toUnsignedString(5, 6) + "\t\t\t 13 = " + Integer.toUnsignedString(13, 6) + "\n" +
//                        "6 = " + Integer.toUnsignedString(6, 6) + "\t\t\t 14 = " + Integer.toUnsignedString(14, 6) + "\n" +
//                        "7 = " + Integer.toUnsignedString(7, 6) + "\t\t\t 15 = " + Integer.toUnsignedString(15, 6) + "\n" +
//                        "8 = " + Integer.toUnsignedString(8, 6) + "\t\t\t 16 = " + Integer.toUnsignedString(16, 6) + "\n";
//                break;
//            case 7:
//                text = "Таблица конвертации из 10-системы в 7-систему:\n" +
//                        "1 = " + Integer.toUnsignedString(1, 7) + "\t\t\t 9 = " + Integer.toUnsignedString(9, 7) + "\n" +
//                        "2 = " + Integer.toUnsignedString(2, 7) + "\t\t\t 10 = " + Integer.toUnsignedString(10, 7) + "\n" +
//                        "3 = " + Integer.toUnsignedString(3, 7) + "\t\t\t 11 = " + Integer.toUnsignedString(11, 7) + "\n" +
//                        "4 = " + Integer.toUnsignedString(4, 7) + "\t\t\t 12 = " + Integer.toUnsignedString(12, 7) + "\n" +
//                        "5 = " + Integer.toUnsignedString(5, 7) + "\t\t\t 13 = " + Integer.toUnsignedString(13, 7) + "\n" +
//                        "6 = " + Integer.toUnsignedString(6, 7) + "\t\t\t 14 = " + Integer.toUnsignedString(14, 7) + "\n" +
//                        "7 = " + Integer.toUnsignedString(7, 7) + "\t\t\t 15 = " + Integer.toUnsignedString(15, 7) + "\n" +
//                        "8 = " + Integer.toUnsignedString(8, 7) + "\t\t\t 16 = " + Integer.toUnsignedString(16, 7) + "\n";
//                break;
//            case 8:
//                text = "Таблица конвертации из 10-системы в 8-систему:\n" +
//                        "1 = " + Integer.toUnsignedString(1, 8) + "\t\t\t 9 = " + Integer.toUnsignedString(9, 8) + "\n" +
//                        "2 = " + Integer.toUnsignedString(2, 8) + "\t\t\t 10 = " + Integer.toUnsignedString(10, 8) + "\n" +
//                        "3 = " + Integer.toUnsignedString(3, 8) + "\t\t\t 11 = " + Integer.toUnsignedString(11, 8) + "\n" +
//                        "4 = " + Integer.toUnsignedString(4, 8) + "\t\t\t 12 = " + Integer.toUnsignedString(12, 8) + "\n" +
//                        "5 = " + Integer.toUnsignedString(5, 8) + "\t\t\t 13 = " + Integer.toUnsignedString(13, 8) + "\n" +
//                        "6 = " + Integer.toUnsignedString(6, 8) + "\t\t\t 14 = " + Integer.toUnsignedString(14, 8) + "\n" +
//                        "7 = " + Integer.toUnsignedString(7, 8) + "\t\t\t 15 = " + Integer.toUnsignedString(15, 8) + "\n" +
//                        "8 = " + Integer.toUnsignedString(8, 8) + "\t\t\t 16 = " + Integer.toUnsignedString(16, 8) + "\n";
//                break;
//            case 9:
//                text = "Таблица конвертации из 10-системы в 9-систему:\n" +
//                        "1 = " + Integer.toUnsignedString(1, 9) + "\t\t\t 9 = " + Integer.toUnsignedString(9, 9) + "\n" +
//                        "2 = " + Integer.toUnsignedString(2, 9) + "\t\t\t 10 = " + Integer.toUnsignedString(10, 9) + "\n" +
//                        "3 = " + Integer.toUnsignedString(3, 9) + "\t\t\t 11 = " + Integer.toUnsignedString(11, 9) + "\n" +
//                        "4 = " + Integer.toUnsignedString(4, 9) + "\t\t\t 12 = " + Integer.toUnsignedString(12, 9) + "\n" +
//                        "5 = " + Integer.toUnsignedString(5, 9) + "\t\t\t 13 = " + Integer.toUnsignedString(13, 9) + "\n" +
//                        "6 = " + Integer.toUnsignedString(6, 9) + "\t\t\t 14 = " + Integer.toUnsignedString(14, 9) + "\n" +
//                        "7 = " + Integer.toUnsignedString(7, 9) + "\t\t\t 15 = " + Integer.toUnsignedString(15, 9) + "\n" +
//                        "8 = " + Integer.toUnsignedString(8, 9) + "\t\t\t 16 = " + Integer.toUnsignedString(16, 9) + "\n";
//                break;
//            case 10:
//                text = "Таблица конвертации из n-системы в 10-систему:\n" +
//                        "1 = 1 \t\t\t 9 = 9 \n" +
//                        "2 = 2 \t\t\t 10 = 10 \n" +
//                        "3 = 3 \t\t\t 11 = 11 \n" +
//                        "4 = 4 \t\t\t 12 = 12 \n" +
//                        "5 = 5 \t\t\t 13 = 13 \n" +
//                        "6 = 6 \t\t\t 14 = 14 \n" +
//                        "7 = 7 \t\t\t 15 = 15 \n" +
//                        "8 = 8 \t\t\t 16 = 16 \n";
//                break;
//            case 11:
//                text = "Таблица конвертации из 10-системы в 11-систему:\n" +
//                        "1 = " + Integer.toUnsignedString(1, 11) + "\t\t\t 9 = " + Integer.toUnsignedString(9, 11) + "\n" +
//                        "2 = " + Integer.toUnsignedString(2, 11) + "\t\t\t 10 = " + Integer.toUnsignedString(10, 11) + "\n" +
//                        "3 = " + Integer.toUnsignedString(3, 11) + "\t\t\t 11 = " + Integer.toUnsignedString(11, 11) + "\n" +
//                        "4 = " + Integer.toUnsignedString(4, 11) + "\t\t\t 12 = " + Integer.toUnsignedString(12, 11) + "\n" +
//                        "5 = " + Integer.toUnsignedString(5, 11) + "\t\t\t 13 = " + Integer.toUnsignedString(13, 11) + "\n" +
//                        "6 = " + Integer.toUnsignedString(6, 11) + "\t\t\t 14 = " + Integer.toUnsignedString(14, 11) + "\n" +
//                        "7 = " + Integer.toUnsignedString(7, 11) + "\t\t\t 15 = " + Integer.toUnsignedString(15, 11) + "\n" +
//                        "8 = " + Integer.toUnsignedString(8, 11) + "\t\t\t 16 = " + Integer.toUnsignedString(16, 11) + "\n";
//                break;
//            case 12:
//                text = "Таблица конвертации из 10-системы в 12-систему:\n" +
//                        "1 = " + Integer.toUnsignedString(1, 12) + "\t\t\t 9 = " + Integer.toUnsignedString(9, 12) + "\n" +
//                        "2 = " + Integer.toUnsignedString(2, 12) + "\t\t\t 10 = " + Integer.toUnsignedString(10, 12) + "\n" +
//                        "3 = " + Integer.toUnsignedString(3, 12) + "\t\t\t 11 = " + Integer.toUnsignedString(11, 12) + "\n" +
//                        "4 = " + Integer.toUnsignedString(4, 12) + "\t\t\t 12 = " + Integer.toUnsignedString(12, 12) + "\n" +
//                        "5 = " + Integer.toUnsignedString(5, 12) + "\t\t\t 13 = " + Integer.toUnsignedString(13, 12) + "\n" +
//                        "6 = " + Integer.toUnsignedString(6, 12) + "\t\t\t 14 = " + Integer.toUnsignedString(14, 12) + "\n" +
//                        "7 = " + Integer.toUnsignedString(7, 12) + "\t\t\t 15 = " + Integer.toUnsignedString(15, 12) + "\n" +
//                        "8 = " + Integer.toUnsignedString(8, 12) + "\t\t\t 16 = " + Integer.toUnsignedString(16, 12) + "\n";
//                break;
//            case 13:
//                text = "Таблица конвертации из 10-системы в 13-систему:\n" +
//                        "1 = " + Integer.toUnsignedString(1, 13) + "\t\t\t 9 = " + Integer.toUnsignedString(9, 13) + "\n" +
//                        "2 = " + Integer.toUnsignedString(2, 13) + "\t\t\t 10 = " + Integer.toUnsignedString(10, 13) + "\n" +
//                        "3 = " + Integer.toUnsignedString(3, 13) + "\t\t\t 11 = " + Integer.toUnsignedString(11, 13) + "\n" +
//                        "4 = " + Integer.toUnsignedString(4, 13) + "\t\t\t 12 = " + Integer.toUnsignedString(12, 13) + "\n" +
//                        "5 = " + Integer.toUnsignedString(5, 13) + "\t\t\t 13 = " + Integer.toUnsignedString(13, 13) + "\n" +
//                        "6 = " + Integer.toUnsignedString(6, 13) + "\t\t\t 14 = " + Integer.toUnsignedString(14, 13) + "\n" +
//                        "7 = " + Integer.toUnsignedString(7, 13) + "\t\t\t 15 = " + Integer.toUnsignedString(15, 13) + "\n" +
//                        "8 = " + Integer.toUnsignedString(8, 13) + "\t\t\t 16 = " + Integer.toUnsignedString(16, 13) + "\n";
//                break;
//            case 14:
//                text = "Таблица конвертации из 10-системы в 14-систему:\n" +
//                        "1 = " + Integer.toUnsignedString(1, 14) + "\t\t\t 9 = " + Integer.toUnsignedString(9, 14) + "\n" +
//                        "2 = " + Integer.toUnsignedString(2, 14) + "\t\t\t 10 = " + Integer.toUnsignedString(10, 14) + "\n" +
//                        "3 = " + Integer.toUnsignedString(3, 14) + "\t\t\t 11 = " + Integer.toUnsignedString(11, 14) + "\n" +
//                        "4 = " + Integer.toUnsignedString(4, 14) + "\t\t\t 12 = " + Integer.toUnsignedString(12, 14) + "\n" +
//                        "5 = " + Integer.toUnsignedString(5, 14) + "\t\t\t 13 = " + Integer.toUnsignedString(13, 14) + "\n" +
//                        "6 = " + Integer.toUnsignedString(6, 14) + "\t\t\t 14 = " + Integer.toUnsignedString(14, 14) + "\n" +
//                        "7 = " + Integer.toUnsignedString(7, 14) + "\t\t\t 15 = " + Integer.toUnsignedString(15, 14) + "\n" +
//                        "8 = " + Integer.toUnsignedString(8, 14) + "\t\t\t 16 = " + Integer.toUnsignedString(16, 14) + "\n";
//                break;
//            case 15:
//                text = "Таблица конвертации из 10-системы в 15-систему:\n" +
//                        "1 = " + Integer.toUnsignedString(1, 15) + "\t\t\t 9 = " + Integer.toUnsignedString(9, 15) + "\n" +
//                        "2 = " + Integer.toUnsignedString(2, 15) + "\t\t\t 10 = " + Integer.toUnsignedString(10, 15) + "\n" +
//                        "3 = " + Integer.toUnsignedString(3, 15) + "\t\t\t 11 = " + Integer.toUnsignedString(11, 15) + "\n" +
//                        "4 = " + Integer.toUnsignedString(4, 15) + "\t\t\t 12 = " + Integer.toUnsignedString(12, 15) + "\n" +
//                        "5 = " + Integer.toUnsignedString(5, 15) + "\t\t\t 13 = " + Integer.toUnsignedString(13, 15) + "\n" +
//                        "6 = " + Integer.toUnsignedString(6, 15) + "\t\t\t 14 = " + Integer.toUnsignedString(14, 15) + "\n" +
//                        "7 = " + Integer.toUnsignedString(7, 15) + "\t\t\t 15 = " + Integer.toUnsignedString(15, 15) + "\n" +
//                        "8 = " + Integer.toUnsignedString(8, 15) + "\t\t\t 16 = " + Integer.toUnsignedString(16, 15) + "\n";
//                break;
//            case 16:
//                text = "Таблица конвертации из 10-системы в 16-систему:\n" +
//                        "1 = " + Integer.toUnsignedString(1, 16) + "\t\t\t 9 = " + Integer.toUnsignedString(9, 16) + "\n" +
//                        "2 = " + Integer.toUnsignedString(2, 16) + "\t\t\t 10 = " + Integer.toUnsignedString(10, 16) + "\n" +
//                        "3 = " + Integer.toUnsignedString(3, 16) + "\t\t\t 11 = " + Integer.toUnsignedString(11, 16) + "\n" +
//                        "4 = " + Integer.toUnsignedString(4, 16) + "\t\t\t 12 = " + Integer.toUnsignedString(12, 16) + "\n" +
//                        "5 = " + Integer.toUnsignedString(5, 16) + "\t\t\t 13 = " + Integer.toUnsignedString(13, 16) + "\n" +
//                        "6 = " + Integer.toUnsignedString(6, 16) + "\t\t\t 14 = " + Integer.toUnsignedString(14, 16) + "\n" +
//                        "7 = " + Integer.toUnsignedString(7, 16) + "\t\t\t 15 = " + Integer.toUnsignedString(15, 16) + "\n" +
//                        "8 = " + Integer.toUnsignedString(8, 16) + "\t\t\t 16 = " + Integer.toUnsignedString(16, 16) + "\n";
//                break;
//        }

        return text;
    }

    private String fillPatternOut(int inSystem, int outSystem) {
        String text = "Таблица конвертации из " + inSystem + "-системы в " + outSystem + "-систему:\n" +
                "1=> " + Integer.toUnsignedString(1, inSystem) + "=" + Integer.toUnsignedString(1, outSystem) + "\t\t9=> " + Integer.toUnsignedString(9, inSystem) + "=" + Integer.toUnsignedString(9, outSystem) + "\n" +
                "2=> " + Integer.toUnsignedString(2, inSystem) + "=" + Integer.toUnsignedString(2, outSystem) + "\t\t10=> " + Integer.toUnsignedString(10, inSystem) + "=" + Integer.toUnsignedString(10, outSystem) + "\n" +
                "3=> " + Integer.toUnsignedString(3, inSystem) + "=" + Integer.toUnsignedString(3, outSystem) + "\t\t11=> " + Integer.toUnsignedString(11, inSystem) + "=" + Integer.toUnsignedString(11, outSystem) + "\n" +
                "4=> " + Integer.toUnsignedString(4, inSystem) + "=" + Integer.toUnsignedString(4, outSystem) + "\t\t12=> " + Integer.toUnsignedString(12, inSystem) + "=" + Integer.toUnsignedString(12, outSystem) + "\n" +
                "5=> " + Integer.toUnsignedString(5, inSystem) + "=" + Integer.toUnsignedString(5, outSystem) + "\t\t13=> " + Integer.toUnsignedString(13, inSystem) + "=" + Integer.toUnsignedString(13, outSystem) + "\n" +
                "6=> " + Integer.toUnsignedString(6, inSystem) + "=" + Integer.toUnsignedString(6, outSystem) + "\t\t14=> " + Integer.toUnsignedString(14, inSystem) + "=" + Integer.toUnsignedString(14, outSystem) + "\n" +
                "7=> " + Integer.toUnsignedString(7, inSystem) + "=" + Integer.toUnsignedString(7, outSystem) + "\t\t15=> " + Integer.toUnsignedString(15, inSystem) + "=" + Integer.toUnsignedString(15, outSystem) + "\n" +
                "8=> " + Integer.toUnsignedString(8, inSystem) + "=" + Integer.toUnsignedString(8, outSystem) + "\t\t16=> " + Integer.toUnsignedString(16, inSystem) + "=" + Integer.toUnsignedString(16, outSystem) + "\n";
        return text;
    }

    private void convertValue() {
        Integer x = 1;
        System.out.println(Integer.parseInt("E", 15));
        System.out.println(Integer.toUnsignedString(12, 13));
    }

    public void selectedInValue() {
        switch (boxIn.getSelectionModel().getSelectedItem().toString()) {
            case "2-система":
                textPatternIn.setText(fillPatternIn(2));
                break;
            case "3-система":
                textPatternIn.setText(fillPatternIn(3));
                break;
            case "4-система":
                textPatternIn.setText(fillPatternIn(4));
                break;
            case "5-система":
                textPatternIn.setText(fillPatternIn(5));
                break;
            case "6-система":
                textPatternIn.setText(fillPatternIn(6));
                break;
            case "7-система":
                textPatternIn.setText(fillPatternIn(7));
                break;
            case "8-система":
                textPatternIn.setText(fillPatternIn(8));
                break;
            case "9-система":
                textPatternIn.setText(fillPatternIn(9));
                break;
            case "10-система":
                textPatternIn.setText(fillPatternIn(10));
                break;
            case "11-система":
                textPatternIn.setText(fillPatternIn(11));
                break;
            case "12-система":
                textPatternIn.setText(fillPatternIn(12));
                break;
            case "13-система":
                textPatternIn.setText(fillPatternIn(13));
                break;
            case "14-система":
                textPatternIn.setText(fillPatternIn(14));
                break;
            case "15-система":
                textPatternIn.setText(fillPatternIn(15));
                break;
            case "16-система":
                textPatternIn.setText(fillPatternIn(16));
                break;
        }
    }
    public void selectedOutValue(){

    }
}
