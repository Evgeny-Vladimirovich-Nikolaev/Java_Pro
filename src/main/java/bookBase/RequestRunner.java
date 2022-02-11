package bookBase;

import receiver.NumberReceiver;
import receiver.StringReceiver;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class RequestRunner {

    bookBase.BookSearcher searcher = new BookSearcher();

    public void startSearching() throws Exception {
        do {
            selectCategory ();
        } while(resume());
    }

    private void selectCategory() throws Exception {
        NumberReceiver receiver = new NumberReceiver(Message.SELECT_REQUEST.getMsg(), 0 , 3);
        int choice = NumberReceiver.call();
        switch(choice) {
            case 1 -> createRequestByTitle();
            case 2 -> createRequestByName();
            case 3 -> createRequestByPrice();
        }
    }

    private void createRequestByTitle() throws Exception {
        StringReceiver receiver = new StringReceiver(Message.ENTER_TITLE.getMsg());
        String bookName = receiver.call();
        searcher.searchBooks("title", bookName);
    }

    private void createRequestByName() throws Exception {
        StringReceiver receiver = new StringReceiver(Message.ENTER_NAME.getMsg());
        String authorName = receiver.call();
        searcher.searchBooks("author", authorName);
    }

    private void createRequestByPrice() {

    }

    boolean resume() {
//        String response = "";
//        while (!"Y".equalsIgnoreCase(response) && !"N".equalsIgnoreCase(response)) {
//            response = receiver.StringReceiver.receive(Message.EXIT_APP.getMsg());
//        }
//        return "N".equalsIgnoreCase(response);
        return true;
    }
}
