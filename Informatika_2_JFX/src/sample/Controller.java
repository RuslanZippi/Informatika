package sample;

import com.sun.org.omg.CORBA.Initializer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;


import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;


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
        if (convert()) {
            if (textIn.getText().contains(",") ||textIn.getText().contains(".") ) {
                textOut.setText(convertDouble());
            } else {
                textOut.setText(Long.toUnsignedString(Long.parseLong(textIn.getText(), getSystem(boxIn)), getSystem(boxOut)));
            }
        }
    }

    private String convertDouble() {
        StringBuilder firstPath = new StringBuilder();
        StringBuilder lastPath = new StringBuilder();
        int radix = getSystem(boxOut);
        long value;
        String s = textIn.getText();
        String splitS = "";
        if (s.contains(".")){
            splitS = "\\.";
        }
        else {
            splitS = ",";
        }
        s = s.split(splitS)[1];
        for (int x = 0; x < 10; x++) {
            value = Long.parseLong(s);
            value *= radix;
            if (String.valueOf(value).length() > s.length()) {
                if (String.valueOf(value).length() - s.length() == 1) {
                    lastPath.append(String.valueOf(value).charAt(0));
                    s = deleteFirstElement(String.valueOf(value));
                }
                if (String.valueOf(value).length() - s.length() == 2) {
                    lastPath.append(convertFirstTwoElements(String.valueOf(value).charAt(0),String.valueOf(value).charAt(1)));
                    s = deleteFirstTwoElements(String.valueOf(value));
                }
            } else {
                lastPath.append("0");
                s = String.valueOf(value);
            }

        }
        String ss = textIn.getText().split(splitS)[0];
        long t = Long.parseLong(ss, getSystem(boxIn));
        firstPath.append(Long.toUnsignedString(t, radix));
        firstPath.append(",").append(lastPath);

        return firstPath.toString();
    }

    private String convertFirstTwoElements(char c1, char c2){
        String s = c1 + String.valueOf(c2);
        switch (s){
            case "10":
                s = "a";
                break;
            case "11":
                s="b";
                break;
            case "12":
                s="c";
                break;
            case "13":
                s="d";
                break;
            case "14":
                s="e";
                break;
            case "15":
                s="f";
                break;
        }
        return s;
    }

    private String deleteFirstTwoElements(String s) {
        String doneS = "";
        for (int x = 2; x < s.length(); x++) {
            doneS += String.valueOf(s.charAt(x));
        }
        return doneS;
    }

    private String deleteFirstElement(String s) {
        String doneS = "";
        for (int x = 1; x < s.length(); x++) {
            doneS += String.valueOf(s.charAt(x));
        }
        return doneS;
    }

    public void buttonClickCheck() {
        if (checkBox.isSelected()) {
            if (textIn.getText().equals("")) {
                textOut.setText("");
            } else {
                if (convert()){
                    if (textIn.getText().contains(",") ||textIn.getText().contains(".") ) {
                        textOut.setText(convertDouble());
                    }
                    else {
                        textOut.setText(Long.toUnsignedString(Long.parseLong(textIn.getText(), getSystem(boxIn)), getSystem(boxOut)));
                    }
                }
            }
        }

    }

    private boolean convert() {
        Pattern p;
        String s = textIn.getText();
        if (s.contains(",")|| s.contains(".")) {
            if (s.split("\\.").length > 2) {
                getAlert();
                return false;
            }
            if (s.split(",").length > 2) {
                getAlert();
                return false;
            } else {
                return true;
            }
        }
        switch (getSystem(boxIn)) {
            case 2:
                p = Pattern.compile("[^01]");
                break;
            case 3:
                p = Pattern.compile("[^012]");
                break;
            case 4:
                p = Pattern.compile("[^0123]");
                break;
            case 5:
                p = Pattern.compile("[^01234]");
                break;
            case 6:
                p = Pattern.compile("[^012345]");
                break;
            case 7:
                p = Pattern.compile("[^0123456]");
                break;
            case 8:
                p = Pattern.compile("[^01234567]");
                break;
            case 9:
                p = Pattern.compile("[^012345678]");
                break;
            case 10:
                p = Pattern.compile("[^0123456789]");
                break;
            case 11:
                p = Pattern.compile("[^0123456789aA]");
                break;
            case 12:
                p = Pattern.compile("[^0123456789aAbB]");
                break;
            case 13:
                p = Pattern.compile("[^0123456789aAbBcC]");
                break;
            case 14:
                p = Pattern.compile("[^0123456789aAbBcCdD]");
                break;
            case 15:
                p = Pattern.compile("[^0123456789aAbBcCdDeE]");
                break;
            case 16:
                p = Pattern.compile("[^0123456789aAbBcCdDeEfF]");
                break;
            default:
                p = Pattern.compile("");

        }
        if (p.matcher(textIn.getText()).find()) {
            getAlert();
            return false;
        } else {
            return true;
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
                error = "Некорректный ввод";
        }
        if (error.equals("Некорректный ввод")) {
            alert.setContentText(error);
        } else {
            alert.setContentText("Разрешено использовать только цифры, в диапозоне: " + error);
        }
        alert.showAndWait();
    }

    public void clickCheckBox(ActionEvent actionEvent) {
        button.setDisable(checkBox.isSelected());
    }

    private String fillPatternIn(int outSystem) {
        String text = "";
        text = "Таблица конвертации из 10-системы в " + outSystem + "-систему:\n" +
                "1 = " + Integer.toUnsignedString(1, outSystem) + "\t\t\t 9 = " + Integer.toUnsignedString(9, outSystem) + "\n" +
                "2 = " + Integer.toUnsignedString(2, outSystem) + "\t\t\t 10 = " + Integer.toUnsignedString(10, outSystem) + "\n" +
                "3 = " + Integer.toUnsignedString(3, outSystem) + "\t\t\t 11 = " + Integer.toUnsignedString(11, outSystem) + "\n" +
                "4 = " + Integer.toUnsignedString(4, outSystem) + "\t\t\t 12 = " + Integer.toUnsignedString(12, outSystem) + "\n" +
                "5 = " + Integer.toUnsignedString(5, outSystem) + "\t\t\t 13 = " + Integer.toUnsignedString(13, outSystem) + "\n" +
                "6 = " + Integer.toUnsignedString(6, outSystem) + "\t\t\t 14 = " + Integer.toUnsignedString(14, outSystem) + "\n" +
                "7 = " + Integer.toUnsignedString(7, outSystem) + "\t\t\t 15 = " + Integer.toUnsignedString(15, outSystem) + "\n" +
                "8 = " + Integer.toUnsignedString(8, outSystem) + "\t\t\t 16 = " + Integer.toUnsignedString(16, outSystem) + "\n";

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

    private int getSystem(ComboBox comboBox) {
        switch (comboBox.getSelectionModel().getSelectedItem().toString()) {
            case "2-система":
                return 2;
            case "3-система":
                return 3;
            case "4-система":
                return 4;
            case "5-система":
                return 5;
            case "6-система":
                return 6;
            case "7-система":
                return 7;
            case "8-система":
                return 8;
            case "9-система":
                return 9;
            case "10-система":
                return 10;
            case "11-система":
                return 11;
            case "12-система":
                return 12;
            case "13-система":
                return 13;
            case "14-система":
                return 14;
            case "15-система":
                return 15;
            case "16-система":
                return 16;
            default:
                return 2;
        }
    }

    public void selectedInValue() {
        textPatternIn.setText(fillPatternIn(getSystem(boxIn)));
        textPatternOut.setText(fillPatternOut(getSystem(boxIn), getSystem(boxOut)));
    }

    public void selectedOutValue() {
        textPatternIn.setText(fillPatternIn(getSystem(boxIn)));
        textPatternOut.setText(fillPatternOut(getSystem(boxIn), getSystem(boxOut)));
    }
}
