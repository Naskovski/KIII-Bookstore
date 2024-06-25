package mk.finki.ukim.mk.wp.lab.web.controller;

import mk.finki.ukim.mk.wp.lab.service.BookEditionService;
import mk.finki.ukim.mk.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/books/editions/{isbn}")
public class BookEditionsController {

    public final BookService bookService;
    public final BookEditionService bookEditionService;

    public BookEditionsController(BookService bookService, BookEditionService bookEditionService) {
        this.bookService = bookService;
        this.bookEditionService = bookEditionService;
    }

    @GetMapping
    public String getEditionsPage(@PathVariable String isbn, Model model){
        model.addAttribute("selectedBook", bookService.findBookByIsbn(isbn));
        model.addAttribute("editions", bookEditionService.findAllByBook_Isbn(isbn));
        return "bookEditions";
    }

    @PostMapping("/filter")
    public String filterEditions(@PathVariable String isbn,
                                 @RequestParam(required = false) String from,
                                 @RequestParam(required = false) String to,
                                 Model model){

        System.out.println(from);
        System.out.println(to);
        if(from.equals("") || to.equals("")){
            model.addAttribute("reviews", bookEditionService.findAllByBook_Isbn(isbn));
        }else {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM");
            YearMonth from_ym = YearMonth.parse(from, fmt);
            LocalDate from_date = from_ym.atDay(1);

            YearMonth to_ym = YearMonth.parse(to, fmt);
            LocalDate to_date = to_ym.atDay(1);

            System.out.println(from_date);
            System.out.println(to_date);

            System.out.println(bookEditionService.findAllByBook_IsbnAndReleaseDateBetween(isbn, from_date, to_date));

            model.addAttribute("editions", bookEditionService.findAllByBook_IsbnAndReleaseDateBetween(isbn, from_date, to_date));
        }

        model.addAttribute("selectedBook", bookService.findBookByIsbn(isbn));

        return "bookEditions";
    }
}
