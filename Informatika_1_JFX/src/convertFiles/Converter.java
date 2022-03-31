package convertFiles;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converter {
    private String doneText;

    private static List<String> splitLine(String line) {
        List<String> list = new ArrayList();
        String[] strings = line.split("\\d");
        String firstWord = strings[0];
        line = line.replace(firstWord, "");
        list.add(firstWord.trim());
        Arrays.stream(line.split(" +")).forEach(x -> {
            list.add(x.trim());
        });


        return list;
    }

    public String convert(String fileText) throws IOException {
        int max = 0;
        int maxLength = 0;
        ArrayList<List<String>> lists = new ArrayList<>();
        Arrays.stream(fileText.split("\n")).forEach(x -> {
            lists.add(splitLine(x));
        });

        for (int x = 0; x < lists.size(); x++) {
            if (lists.get(x).size() > max) {
                max = lists.get(x).size();
            }
            for (int y = 0; y < lists.get(x).size(); y++) {
                if (lists.get(x).get(y).length() > maxLength) {
                    maxLength = lists.get(x).get(y).length();
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < max; x++) {
            for (int y = 0; y < lists.size(); y++) {
                if (x >= lists.get(y).size()) {
                    builder.append(addSpace("",maxLength));
                } else {
                    builder.append(addSpace(lists.get(y).get(x),maxLength));
                    System.out.println(lists.get(y).get(x).length());
                }
            }
            builder.append("\n");
        }
        doneText = builder.toString();
        return doneText;
    }

    public void writeFile(String filePath, String newFile) throws IOException {
        FileWriter writer = new FileWriter(filePath + newFile);
        writer.write(doneText);
        writer.flush();
        writer.close();
    }

    private String addSpace(String string, int maxLength) {
        StringBuilder builder = new StringBuilder(string);
        int needCount = maxLength - string.length() + 3;
        for (int x = 0; x < needCount; x++) {
            builder.append(" ");
        }
        return builder.toString();
    }
}
