package mk.finki.ukim.mk.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.wp.lab.model.Book;
import mk.finki.ukim.mk.wp.lab.model.BookStore;
import mk.finki.ukim.mk.wp.lab.service.impl.BookServiceImpl;
import mk.finki.ukim.mk.wp.lab.service.impl.BookStoreServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path={"/books", "/"})
public class BookController {

    private final BookServiceImpl bookService;
    private final BookStoreServiceImpl bookStoreService;

    public BookController(BookServiceImpl bookService, BookStoreServiceImpl bookStoreService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("books", bookService.listBooks());
        return "listBooks";
    }

    @PostMapping("/select")
    public String selectBook(@RequestParam String bookIsbn){
        return "redirect:/books/" +
                bookIsbn +
                "/add_author";
    }

    @GetMapping("/add-form")
    public String getAddBookPage(Model model){
        model.addAttribute("bookstores", bookStoreService.findAll());
        return "add-book";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        Book book = bookService.findBookById(id);

        if(book == null) {
            redirectAttributes.addFlashAttribute("error", "No book found matching the id");
            return "redirect:/books"; //todo dodadi error msg
        }

        model.addAttribute("bookstores", bookStoreService.findAll());
        model.addAttribute("book", book);
        return "add-book";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title, @RequestParam String isbn,
                           @RequestParam String genre, @RequestParam int year,
                           @RequestParam long bookstore, Model model){

        BookStore store = bookStoreService.findById(bookstore);
        bookService.addBook(new Book(isbn, title, genre, year, store));

        model.addAttribute("books", bookService.listBooks());
        return "redirect:/books";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId, @RequestParam (required = false) String title,
                           @RequestParam (required = false) String isbn, @RequestParam (required = false) String genre,
                           @RequestParam (required = false) int year, @RequestParam (required = false) long bookstore, Model model){

        bookService.updateBook(bookId, title, isbn, genre, year, bookstore);

        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, Model model){
        bookService.deleteBook(bookService.findBookById(id));

        model.addAttribute("books", bookService.listBooks());
        return "redirect:/books";
    }

}
