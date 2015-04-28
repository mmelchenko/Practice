package old.IOpractice;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * Использование формата ZIP  для сжатия любого количества файлов, указанніх в командной строке.
 */
public class ZipCompress {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("Practice.zip");
        CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new Adler32());
        ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream);
        BufferedOutputStream out = new BufferedOutputStream(zipOutputStream);
        zipOutputStream.setComment("Проверка ZIP-сжатия Java");
        //Однако парного метода для получения комментария
        //getComment() не существует
        for(String arg : args) {
            System.out.println("Запись файла " + arg);
            BufferedReader in = new BufferedReader(new FileReader(arg));
            zipOutputStream.putNextEntry(new ZipEntry(arg));
            int c;
            while((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.flush();
        }
        out.close();
        // Контрольная сумма становится действительной
        // только после закрытия файла с архивом
        System.out.println("Checksum " + checkedOutputStream.getChecksum().getValue());
        // Теперь извлекаем файлы
        System.out.println("Чтение файла");
        FileInputStream fileInputStream = new FileInputStream("Practice.zip");
        CheckedInputStream checkedSum = new CheckedInputStream(fileInputStream, new Adler32());
        ZipInputStream zipInputStream = new ZipInputStream(checkedSum);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(zipInputStream);
        ZipEntry zipEntry;
        while((zipEntry = zipInputStream.getNextEntry()) != null) {
            System.out.println("Читаем файл " + zipEntry);
            int x;
            while((x = bufferedInputStream.read()) != -1) {
                System.out.write(x);
            }
        }
        if(args.length == 1) {
            System.out.println("Контрольная сумма " + + checkedSum.getChecksum().getValue());
        }
        bufferedInputStream.close();
        // Альтернативный способ открытия и чтения
        // файлов в формате ZIP
        ZipFile zipFile = new ZipFile("Practice.zip");
        Enumeration enumeration = zipFile.entries();
        while(enumeration.hasMoreElements()) {
            ZipEntry zipEntry1 = (ZipEntry) enumeration.nextElement();
            System.out.println("Файл " + zipEntry1);
            ///... затем данные извлекаются как и прежде
        }
    }
}
