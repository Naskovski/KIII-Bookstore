package mk.finki.ukim.mk.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name="book_editions")
public class BookEdition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Book book;
    private String publisher;
    @DateTimeFormat(pattern = "MM-yyyy-dd")
    private LocalDate releaseDate;
    private int numCopies;






}
