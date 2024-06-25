package mk.finki.ukim.mk.wp.lab.web.controller;

import mk.finki.ukim.mk.wp.lab.model.Review;
import mk.finki.ukim.mk.wp.lab.service.BookService;
import mk.finki.ukim.mk.wp.lab.service.ReviewService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/books/review/{isbn}")
public class ReviewsController {

    private final ReviewService reviewService;
    private final BookService bookService;

    public ReviewsController(ReviewService reviewService, BookService bookService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getReviewsPage(@PathVariable String isbn, Model model){
        model.addAttribute("selectedBook", bookService.findBookByIsbn(isbn));
        model.addAttribute("reviews", reviewService.findAllByBookIsbn(isbn));
        return "reviews";
    }

    @PostMapping
    public String submitReview(@PathVariable String isbn, @RequestParam int score,
                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp,
                               @RequestParam String desc,  Model model){
        reviewService.add(new Review(score, desc, bookService.findBookByIsbn(isbn), timestamp));

        model.addAttribute("selectedBook", bookService.findBookByIsbn(isbn));
        model.addAttribute("reviews", reviewService.findAllByBookIsbn(isbn));
        return "redirect:/books/review/{isbn}";
    }


    @PostMapping("/filter")
    public String filterReviews(@PathVariable String isbn,
                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime from,
                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                Model model){
        if(from == null || to == null){
            model.addAttribute("reviews", reviewService.findAllByBookIsbn(isbn));
        }else model.addAttribute("reviews", reviewService.findAllByBook_IsbnAndTimestampBetween(isbn, from, to));

        model.addAttribute("selectedBook", bookService.findBookByIsbn(isbn));

        return "reviews";
    }





}
