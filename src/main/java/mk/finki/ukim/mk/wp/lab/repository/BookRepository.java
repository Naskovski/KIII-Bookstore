package mk.finki.ukim.mk.wp.lab.repository;

import mk.finki.ukim.mk.wp.lab.model.Author;
import mk.finki.ukim.mk.wp.lab.model.Book;
import mk.finki.ukim.mk.wp.lab.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByIsbn(String text);
    Book findBookById(Long id);
    List<Book> findAllByAuthors_Id(Long AuthorId);

    //Author addAuthorToBook(Long authorId, String isbn);
    //Author removeAuthorFromBook(Long authorId, String isbn);
    Book deleteBookByIsbn(String Isbn);
    //Book addBook(Book book);
    //Book deleteBook(Book book);



}
