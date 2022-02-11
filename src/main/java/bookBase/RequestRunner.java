package bookBase;

import lombok.SneakyThrows;
import receiver.ValueReceiver;

import java.sql.SQLException;

public class RequestRunner {

    bookBase.BookSearcher searcher = new BookSearcher();

    public void startSearching() throws Exception {
        do {
            selectCategory ();
        } while(resume());
    }

    private void selectCategory() {
        String choice = ValueReceiver.receiveString(Message.SELECT_REQUEST.getMsg());
        switch(choice) {
            case "1" -> createRequestByTitle();
            case "2" -> createRequestByName();
            case "3" -> createRequestByPrice();
        }
    }

    private void createRequestByTitle() {
        String bookName = ValueReceiver.receiveString(Message.ENTER_TITLE.getMsg());
        searcher.searchBooks("title", bookName);
    }

    private void createRequestByName() {
        String authorName = ValueReceiver.receiveString(Message.ENTER_NAME.getMsg());
        searcher.searchBooks("author", authorName);
    }

    private void createRequestByPrice() {
        String price = Integer.toString(ValueReceiver.receiveInt(Message.ENTER_PRICE.getMsg()));
        searcher.searchBooks("price", price);
    }

    boolean resume() {
        String response = "";
        while (!"Y".equalsIgnoreCase(response) && !"N".equalsIgnoreCase(response)) {
            response = ValueReceiver.receiveString(Message.EXIT_APP.getMsg());
        }
        return "N".equalsIgnoreCase(response);
    }
}
