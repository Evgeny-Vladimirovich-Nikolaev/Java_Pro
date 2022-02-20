package ormBookBase;

import bookBase.Message;
import ormBookBase.dao.BookDao;
import ormBookBase.dao.BookDaoImpl;
import ormBookBase.dto.Book;
import receiver.ValueReceiver;

import java.util.List;

public class Controller {

    private final BookDao bookDao = new BookDaoImpl();

    public void startSearching() {
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
        List<Book> results = bookDao.searchByTitle(bookName);
        for (Book book : results) {
            System.out.println(book);
        }
    }

    private void createRequestByName() {
        String authorName = ValueReceiver.receiveString(Message.ENTER_NAME.getMsg());
        List<Book> results = bookDao.searchByAuthor(authorName);
        for (Book book : results) {
            System.out.println(book);
        }
    }

    private void createRequestByPrice() {
        int price = ValueReceiver.receiveInt(Message.ENTER_PRICE.getMsg());
        List<Book> results = bookDao.searchByPrice(price);
        for (Book book : results) {
            System.out.println(book);
        }
    }

    boolean resume() {
        String response = "";
        while (!"Y".equalsIgnoreCase(response) && !"N".equalsIgnoreCase(response)) {
            response = ValueReceiver.receiveString(Message.EXIT_APP.getMsg());
        }
        return "N".equalsIgnoreCase(response);
    }
}
