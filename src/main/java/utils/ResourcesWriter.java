import java.io.BufferedWriter;
import java.io.IOException;

public class ResourcesWriter {

    private ResourcesWriter() {}

    public static synchronized void writeFile(String path, String text, boolean append) {
        try {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(path, append));
            writer.write(text);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
