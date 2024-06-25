package mk.finki.ukim.mk.wp.lab.service.impl;

import mk.finki.ukim.mk.wp.lab.model.Author;
import mk.finki.ukim.mk.wp.lab.model.Book;
import mk.finki.ukim.mk.wp.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.wp.lab.repository.BookRepository;
import mk.finki.ukim.mk.wp.lab.repository.BookStoreRepository;
import mk.finki.ukim.mk.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookStoreRepository bookStoreRepository;

    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, BookStoreRepository bookStoreRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Author author = authorRepository.findById(authorId).orElse(null);
        Book book = bookRepository.findBookByIsbn(isbn);
        book.addAuthor(author);
        bookRepository.save(book);
        return author;
    }

    @Override
    public Author removeAuthorFromBook(Long authorId, String isbn) {
        Author author = authorRepository.findById(authorId).orElse(null);
        Book book = bookRepository.findBookByIsbn(isbn);
        book.removeAuthor(author);
        bookRepository.save(book);
        return author;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findBookByIsbn(isbn);
    }
    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public List<Book> findAllBooksByAuthor(Long authorId) {
        return bookRepository.findAllByAuthors_Id(authorId);
    }
    @Override
    public Book updateBook(Long bookId, String title, String isbn,String genre,int year,Long bookstore){
        Book book = bookRepository.findBookById(bookId);

        if(book == null) return null;

        if(!title.equals("")) book.setTitle(title);
        if(!isbn.equals("")) book.setIsbn(isbn);
        if(!genre.equals("")) book.setGenre(genre);
        book.setYear(year);
        book.setBookStore(bookStoreRepository.findById(bookstore).get());
        bookRepository.save(book);
        return book;
    }
}
