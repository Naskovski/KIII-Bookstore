package mk.finki.ukim.mk.wp.lab.web.controller;

import mk.finki.ukim.mk.wp.lab.model.BookStore;
import mk.finki.ukim.mk.wp.lab.service.impl.BookStoreServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bookstores")
public class BookstoreController {


    private final BookStoreServiceImpl bookStoreService;

    public BookstoreController(BookStoreServiceImpl bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public String getBookstoresPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("bookstores", bookStoreService.findAll());
        return "listBookstores";
    }

    @GetMapping("/add-store-form")
    public String getAddBookPage(Model model){
        return "add-store";
    }

    @GetMapping("/edit-store-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes){
        BookStore bookStore = bookStoreService.findById(id);
        if(bookStore == null) {
            redirectAttributes.addFlashAttribute("error", "No store found matching the id");
            return "redirect:/bookstores";
        }

        model.addAttribute("bookstore",bookStore);
        return "add-store";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String name, @RequestParam String address,
                           @RequestParam String city, Model model){


        bookStoreService.addStore(new BookStore(name, city, address));
        model.addAttribute("bookstores", bookStoreService.findAll());
        return "redirect:/bookstores";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @RequestParam (required = false) String name,
                           @RequestParam (required = false) String address, @RequestParam (required = false) String city, Model model){

        bookStoreService.updateBookStore(id, name, address, city);

        return "redirect:/bookstores";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, Model model){

        bookStoreService.deleteStore(bookStoreService.findById(id));
        model.addAttribute("bookstores", bookStoreService.findAll());
        return "redirect:/bookstores";
    }

}