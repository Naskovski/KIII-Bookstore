package mk.finki.ukim.mk.wp.lab.service;

import mk.finki.ukim.mk.wp.lab.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> listAuthors();
    List<Author> findAll();
    Author findById(Long id);

}