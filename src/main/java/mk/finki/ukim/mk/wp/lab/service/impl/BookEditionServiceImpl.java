package mk.finki.ukim.mk.wp.lab.service.impl;

import mk.finki.ukim.mk.wp.lab.model.BookEdition;
import mk.finki.ukim.mk.wp.lab.repository.BookEditionRepository;
import mk.finki.ukim.mk.wp.lab.service.BookEditionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
public class BookEditionServiceImpl implements BookEditionService {

    private final BookEditionRepository bookEditionRepository;

    public BookEditionServiceImpl(BookEditionRepository bookEditionRepository) {
        this.bookEditionRepository = bookEditionRepository;
    }

    @Override
    public List<BookEdition> findAllByBook_Isbn(String isbn) {
        return bookEditionRepository.findAllByBook_Isbn(isbn);
    }

    @Override
    public List<BookEdition> findAllByBook_IsbnAndReleaseDateBetween(String isbn, LocalDate from, LocalDate to) {
        return bookEditionRepository.findAllByBook_IsbnAndReleaseDateBetween(isbn, from, to);
    }
}
