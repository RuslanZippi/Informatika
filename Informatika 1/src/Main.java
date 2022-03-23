import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static ArrayList<String> list = new ArrayList<>();
    static ArrayList<List<String>> lists = new ArrayList<>();
    static boolean checker = false;
    static int x = 0;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\rusla\\OneDrive\\Рабочий стол";

        File file = new File(path + "\\text.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        list.forEach(System.out::println);
        while (reader.ready()) {
            lists.add(splitLine(reader.readLine()));
        }
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < 19; x++) {
            for (int y = 0; y < lists.size(); y++) {
                if (x >= lists.get(y).size()) {
                    builder.append("\t\t\t");
                } else {
                    builder.append(lists.get(y).get(x) + "\t\t\t");
                }
            }
            checker = true;
            builder.append("\n");
        }

        FileWriter writer = new FileWriter(path + "\\newText.txt");
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }

    public static List<String> splitLine(String line) {
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

    public static void getMax(int size) {
        System.out.println(size);
        if (size > max) {
            max = size;
        }
    }
}
