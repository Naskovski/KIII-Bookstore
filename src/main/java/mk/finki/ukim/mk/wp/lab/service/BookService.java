package mk.finki.ukim.mk.wp.lab.service;

import mk.finki.ukim.mk.wp.lab.model.Author;
import mk.finki.ukim.mk.wp.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Author removeAuthorFromBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);
    Book findBookById(Long id);

    Book addBook(Book book);

    void deleteBook(Book book);
    List<Book> findAllBooksByAuthor(Long authorId);
    Book updateBook(Long bookId, String title, String isbn,String genre,int year,Long bookstore);
}
