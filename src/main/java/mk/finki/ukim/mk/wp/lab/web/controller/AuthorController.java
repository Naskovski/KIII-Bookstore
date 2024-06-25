package mk.finki.ukim.mk.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.finki.ukim.mk.wp.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.wp.lab.repository.BookRepository;
import mk.finki.ukim.mk.wp.lab.service.AuthorService;
import mk.finki.ukim.mk.wp.lab.service.BookService;
import mk.finki.ukim.mk.wp.lab.service.impl.AuthorServiceImpl;
import mk.finki.ukim.mk.wp.lab.service.impl.BookServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books/{selectedBookIsbn}/add_author")
public class AuthorController {


    private final AuthorServiceImpl authorService;
    private final BookServiceImpl bookService;

    public AuthorController(AuthorServiceImpl authorService, BookServiceImpl bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getAuthorsPage(@RequestParam(required = false) String error, @PathVariable String selectedBookIsbn, Model model){
        model.addAttribute("selectedBook", bookService.findBookByIsbn(selectedBookIsbn));
        model.addAttribute("authors", authorService.findAll());
        return "authorList";
    }

    @PostMapping
    public String addAuthorToBook(@PathVariable String selectedBookIsbn, @RequestParam String authorId, HttpServletRequest req){
        bookService.addAuthorToBook(Long.valueOf(authorId), selectedBookIsbn);
        return "redirect:/books/" +
                selectedBookIsbn +
                "/details?authorId=" + authorId;
    }
}
