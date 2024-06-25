package mk.finki.ukim.mk.wp.lab.service;

import mk.finki.ukim.mk.wp.lab.model.BookEdition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

public interface BookEditionService {
    public List<BookEdition> findAllByBook_Isbn(String isbn);
    public List<BookEdition> findAllByBook_IsbnAndReleaseDateBetween(String isbn, LocalDate from, LocalDate to);
}
