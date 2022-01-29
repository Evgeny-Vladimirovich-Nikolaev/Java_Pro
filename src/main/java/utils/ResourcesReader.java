import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ResourcesReader {

    private ResourcesReader() {}

    private static InputStream getFileFromResourceAsStream(String fileName) {
        try {
            Class cls = ResourcesReader.class;
            InputStream inputStream = cls.getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new FileNotFoundException("Файл " + fileName + " не найден");
            } else {
                return inputStream;
            }
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static ArrayList<String> readByLines(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        InputStream stream = getFileFromResourceAsStream(fileName);
        try (InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("Не удалось прочитать файл ресурсов " + fileName);
            lines.add("нет данных");
        } finally {
            try {
                stream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Ошибка ввода/вывода: не удалось закрыть поток");
            }
        }
        return lines;
    }

    public static String readText(String fileName) {
        StringBuilder sb = new StringBuilder();
        InputStream stream = getFileFromResourceAsStream(fileName);
        try (InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("Не удалось прочитать файл ресурсов" + fileName);
        } finally {
            try {
                stream.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Ошибка ввода/вывода: не удалось закрыть поток");
            }
        }
        return sb.toString();
    }

    public static void printConsole(String path) throws IOException {
        FileReader reader = new FileReader(path);
        int c;
        while ((c = reader.read()) != -1) {
            System.out.print((char) c);
        }
    }
}
