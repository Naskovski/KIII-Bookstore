package mk.finki.ukim.mk.wp.lab.service;

import mk.finki.ukim.mk.wp.lab.model.Book;
import mk.finki.ukim.mk.wp.lab.model.BookStore;

import java.util.List;

public interface BookStoreService {

    public List<BookStore> findAll();

    public BookStore findById(long id);

    public void addStore(BookStore store);
    public void deleteStore(BookStore store);
    public BookStore updateBookStore(Long id, String name, String address, String city);
}
