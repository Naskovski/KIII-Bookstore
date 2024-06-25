package mk.finki.ukim.mk.wp.lab.web.controller;

import mk.finki.ukim.mk.wp.lab.service.AuthorService;
import mk.finki.ukim.mk.wp.lab.service.BookService;
import mk.finki.ukim.mk.wp.lab.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books/{selectedBookIsbn}/details")
public class BookDetailsController {

    private final AuthorService authorService;
    private final BookService bookService;
    private final ReviewService reviewService;

    public BookDetailsController(AuthorService authorService, BookService bookService, ReviewService reviewService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.reviewService = reviewService;
    }


    @GetMapping
    public String getDetails(@PathVariable String selectedBookIsbn, @RequestParam (required = false) String authorId, Model model){
        model.addAttribute("selectedBook", bookService.findBookByIsbn(selectedBookIsbn));
        model.addAttribute("booksByAuthor", bookService.findAllBooksByAuthor(Long.valueOf(authorId)));
        model.addAttribute("selectedAuthor", authorService.findById(Long.valueOf(authorId)));
        return "bookDetails";
    }

    @PostMapping()
    public String removeAuthorFromBook(@PathVariable String selectedBookIsbn, @RequestParam String selectedBookRemoveFrom, @RequestParam String authorId, Model model){
        bookService.removeAuthorFromBook(Long.valueOf(authorId), selectedBookRemoveFrom);

        model.addAttribute("selectedBook", bookService.findBookByIsbn(selectedBookIsbn));
        model.addAttribute("booksByAuthor", bookService.findAllBooksByAuthor(Long.valueOf(authorId)));
        model.addAttribute("selectedAuthor", authorService.findById(Long.valueOf(authorId)));


        return "bookDetails";
    }
}
