package ormBookBase.controller;

import lombok.RequiredArgsConstructor;
import ormBookBase.dao.BookDao;
import ormBookBase.dto.Book;
import receiver.ValueReceiver;

import java.util.List;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class ControllerImpl implements Controller{

    private final BookDao bookDao;

    @Override
    public void startSearching() {
        do {
            selectCategory ();
        } while(resume());
    }

    private void selectCategory() {
        String choice = ValueReceiver.receiveString(Message.SELECT_REQUEST.getMsg());
        List<Book> books;
        switch(choice) {
            case "1" -> books = createRequestByTitle();
            case "2" -> books = createRequestByName();
            case "3" -> books = createRequestByPrice();
            default -> {
                return;
            }
        }
        if (!books.isEmpty()) {
            for(Book book : books) {
                System.out.println(book);
            }
        } else {
            System.out.println(Message.NO_RESULTS.getMsg());
        }
    }

    private List<Book> createRequestByTitle() {
        String bookName = ValueReceiver.receiveString(Message.ENTER_TITLE.getMsg());
        return bookDao.searchByTitle(bookName);
    }

    private List<Book> createRequestByName() {
        String authorName = ValueReceiver.receiveString(Message.ENTER_NAME.getMsg());
        return bookDao.searchByAuthor(authorName);
    }

    private List<Book> createRequestByPrice() {
        int price = ValueReceiver.receiveInt(Message.ENTER_PRICE.getMsg());
        return bookDao.searchByPrice(price);
    }

    private boolean resume() {
        String response = "";
        while (!"Y".equalsIgnoreCase(response) && !"N".equalsIgnoreCase(response)) {
            response = ValueReceiver.receiveString(Message.EXIT_APP.getMsg());
        }
        return "N".equalsIgnoreCase(response);
    }
}
