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
        ArrayList<List<String>> lists = new ArrayList<>();
        Arrays.stream(fileText.split("\n")).forEach(x-> {
            lists.add(splitLine(x));
        });

        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < lists.size(); y++) {
                if (x >= lists.get(y).size()) {
                    builder.append("\t\t\t");
                } else {
                    builder.append(lists.get(y).get(x) + "\t\t\t");
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
}
