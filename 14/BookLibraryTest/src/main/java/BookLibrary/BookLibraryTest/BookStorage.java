package BookLibrary.BookLibraryTest;

import response.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookStorage {

    private static int counter = 1;
    private static HashMap<Integer, Book> bookLibrary = new HashMap<>();

    public static int addBook(Book book) {
        book.setId(counter++);
        bookLibrary.put(book.getId(), book);
        return book.getId();
    }

    public static List<Book> getBookList () {
        return new ArrayList(bookLibrary.values());
    }

    public static Book getBookFromId (int id) {
        return bookLibrary.get(id);
    }

    public static Book deleteBook (int id) {
        return bookLibrary.remove(id);
    }

}
