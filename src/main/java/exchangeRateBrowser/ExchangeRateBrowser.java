package exchangeRateBrowser;

public class ExchangeRateBrowser {

    public static void main(String[] args) {
        byte[] a = new byte[] {49, 50, 51, 52, 53, 54, 55};
        String b = new String(a);
        System.out.println(b);
        String c = new String(a, 2, 4);
        System.out.println(c);
    }

}
