import java.io.IOException;
import java.net.*;
import java.util.Scanner;


public class WeatherService {

    public static void main(String[] args) throws URISyntaxException, IOException {
        URI uri = new URI("https://yandex.ru/dev/weather/doc/dg/concepts/about.html");

        System.out.println("Протокол: " + uri.getScheme());
        System.out.println("Хост: " + uri.getHost());
        System.out.println("Порт: " + uri.getPort());
        System.out.println("Путь: " + uri.getPath());
        System.out.println("Параметры: " + uri.getRawQuery());
        System.out.println("Фрагмент: " + uri.getFragment());
        System.out.println("------------");
        URL url = uri.toURL();
        URLConnection urlConnection = url.openConnection();
//        urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36");
        urlConnection.connect();
        Scanner scanner = new Scanner(urlConnection.getInputStream());
//        Scanner scanner = new Scanner(url.openStream());
        while(scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        System.out.println("-----------------");

    }

}
