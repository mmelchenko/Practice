package IOpractice;

import java.io.*;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Буферизированное чтение из файла
 */
public class BufferedReading {

    public static String read(String fileName) throws IOException {
//        так можно прочесть в нужной кодировке если вдруг она не родная
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
//      лучше инициализирить явно всегда, так виднее когда читаешь потом
        String string = null;
        StringBuilder stringBuilder = new StringBuilder();

        /* 6-чный способ читать*/
//        while ((string = in.readLine()) != null) {
//          сепаратор в разных осях разный, мало того + создаёт стринги, а мы билдером этого стараемся избегать
//            stringBuilder.append(string).append(System.getProperty("line.separator"));
//        }

//        8-чный вариант со стримом
        for (Iterator<String> is = in.lines().iterator(); is.hasNext(); )
            stringBuilder.append(is.next()).append(System.lineSeparator());

//        сразу закрывать нельзя, а вдруг ты не смог открыть и там null - будет NullPointerException, а ты ожидаешь IOException
        try {
            in.close();
        } catch (Exception e) {
        }
//        либо
        if (in != null) {
            in.close();
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        String path = BufferedReading.class.getPackage().getName();
        System.out.print(read("src/main/java/" + path + "/BufferedReading.java"));
    }

//    Задача:
//    есть символьный файл A.txt - надо прочитать его последние 20 символов
//    делай при помощи RandomAccessFile
}
