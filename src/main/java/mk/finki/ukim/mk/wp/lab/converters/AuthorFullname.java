package mk.finki.ukim.mk.wp.lab.converters;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorFullname implements Serializable {
    private String name;
    private String surname;

}
