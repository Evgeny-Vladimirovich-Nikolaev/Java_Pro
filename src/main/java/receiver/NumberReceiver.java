import java.io.IOException;

public class NumberReceiver extends Receiver {

    public int receiveInt(String conditions) {
        int value;
        String temp = "";
        while (true) {
            System.out.println(conditions);
            try {
                temp = inputReader.readLine();
                value = Integer.parseInt(temp);
            } catch (NumberFormatException numEx) {
                if (temp.length() != 0 && checkLine(temp)) {
                    System.out.println(temp
                            + ReceiverMessage.INVALID_NUMBER.getMsg());
                } else {
                    System.out.println(temp + ReceiverMessage.INVALID_LINE.getMsg());
                }
                continue;
            } catch (IOException ioEx) {
                System.out.println(ReceiverMessage.IO_ERROR.getMsg());
                continue;
            } catch (Exception ex) {
                System.out.println(ReceiverMessage.ERROR.getMsg());
                continue;
            }
            return value;
        }
    }

    private boolean checkLine(/*@NotNull*/ String line) {
        char[] ch = line.toCharArray();
        if (ch[0] != 45 && (ch[0] < 48 || ch[0] > 57)) {
            return false;
        }
        for (int i = 1; i < ch.length; i++) {
            if (ch[i] < 48 || ch[i] > 57)
                return false;
        }
        return true;
    }
}
