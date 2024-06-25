package mk.finki.ukim.mk.wp.lab.repository;

import mk.finki.ukim.mk.wp.lab.model.BookEdition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookEditionRepository extends JpaRepository<BookEdition, Long> {

    public List<BookEdition> findAllByBook_Isbn(String isbn);

    public List<BookEdition> findAllByBook_IsbnAndReleaseDateBetween(String isbn, LocalDate from, LocalDate to);
}
