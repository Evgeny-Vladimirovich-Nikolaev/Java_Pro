package bookBase;

import receiver.NumberReceiver;
import receiver.StringReceiver;

public class requestRunner {

    public void startSearching() {
        do {
            selectCategory ();
        } while(resume());
    }

    private void selectCategory () {
        int choice = NumberReceiver.receive(Message.SELECT_REQUEST.getMsg(), 0 , 3);
//        switch(choice) {
//            case 1 :
//        }
    }

    boolean resume() {
        String response = "";
        while (!"Y".equalsIgnoreCase(response) && !"N".equalsIgnoreCase(response)) {
            response = StringReceiver.receive(Message.EXIT_APP.getMsg());
        }
        return "N".equalsIgnoreCase(response);
    }
}
