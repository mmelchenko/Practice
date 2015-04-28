package old.IOpractice;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Построчное считывание и запись в файл,
 * а так же манипуляции файлом как списком ArrayList.
 */
public class ReadAndWriteFromFile extends ArrayList<String>{
    public static String read(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));

            try {
                String s;
                while((s = in.readLine()) != null) {
                    stringBuilder.append(s);
                    stringBuilder.append("\n");
                }
            } finally {
                in.close();
            }

        } catch (IOException e) {
            throw new RuntimeException();
        }
        return stringBuilder.toString();
    }

    public static void write(String fileName, String text) {
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());

            try {
                out.print(text);
            } finally {
                out.close();
            }

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    //Чтение файла с разбиением по регулярному выражению
    public ReadAndWriteFromFile(String fileName, String splitter) {
        super(Arrays.asList(read(fileName).split(splitter)));
        if(get(0).equals("")) {
            remove(0);
        }
    }

    //Обычное построчное чтение
    public ReadAndWriteFromFile(String fileMame) {
        this(fileMame, "\n");
    }

    public void write(String fileName) {
        try {
            PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());

            try {
                for(String item : this) {
                    out.println(item);
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        String file = read("ReadAndWriteFromFile.java");
        write("test.txt", file);
        ReadAndWriteFromFile text = new ReadAndWriteFromFile("test.txt");
        text.write("test2.txt");
        TreeSet<String> words = new TreeSet<String>(new ReadAndWriteFromFile("ReadAndWriteFromFile.java", "\\W+"));
        System.out.println(words.headSet("a"));
    }
}
