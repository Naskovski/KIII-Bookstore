package mk.finki.ukim.mk.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.finki.ukim.mk.wp.lab.converters.AuthorFullname;
import mk.finki.ukim.mk.wp.lab.converters.AuthorFullnameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name="authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = AuthorFullnameConverter.class)
    private AuthorFullname authorFullName;
    @Column(length = 3000)
    private String biography;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    public Author() {
    }
    public Author(String name, String surname, String biography, LocalDate dateOfBirth) {
        this.authorFullName = new AuthorFullname();
        this.authorFullName.setName(name);
        this.authorFullName.setSurname(surname);

        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
    }
    public Author(String name, String surname, String biography) {
        this.authorFullName = new AuthorFullname();
        this.authorFullName.setName(name);
        this.authorFullName.setSurname(surname);

        this.biography = biography;
        this.dateOfBirth = null; //dali?
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(authorFullName, author.authorFullName) && Objects.equals(biography, author.biography) && Objects.equals(dateOfBirth, author.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorFullName, biography, dateOfBirth);
    }

    public String getName(){
        return authorFullName.getName();
    }
    public String getSurname(){
        return authorFullName.getSurname();
    }
}
