package BookLibrary.BookLibraryTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import response.Book;

import java.util.Date;
import java.util.List;

@RestController
public class BookController {

    @RequestMapping(value = "/books/", method = RequestMethod.POST)
    public int addBook(Book book) {
        return BookStorage.addBook(book);
    }

    @RequestMapping(value = "/books/", method = RequestMethod.GET)
    public List<Book> getList() {
        return BookStorage.getBookList();
    }

    @RequestMapping(value ="/books/{id}", method = RequestMethod.GET)
    public ResponseEntity getBook(@PathVariable int id) {
        Book book = BookStorage.getBookFromId(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delBook(@PathVariable int id) {
        Book book = BookStorage.deleteBook(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
