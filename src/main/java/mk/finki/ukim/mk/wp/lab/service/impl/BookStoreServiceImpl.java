package mk.finki.ukim.mk.wp.lab.service.impl;

import mk.finki.ukim.mk.wp.lab.model.BookStore;
import mk.finki.ukim.mk.wp.lab.repository.BookStoreRepository;
import mk.finki.ukim.mk.wp.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    private final BookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

    @Override
    public BookStore findById(long id) {
        return bookStoreRepository.findById(id).orElse(null);
    }

    @Override
    public void addStore(BookStore store) {
        bookStoreRepository.save(store);//dali?
    }

    @Override
    public void deleteStore(BookStore store) {
        bookStoreRepository.delete(store);
    }

    @Override
    public BookStore updateBookStore(Long id, String name, String address, String city){
        BookStore store = bookStoreRepository.findById(id).orElse(null);

        if(store == null) return null;

        if(!name.equals("")) store.setName(name);
        if(!address.equals("")) store.setAddress(address);
        if(!city.equals("")) store.setCity(city);
        bookStoreRepository.save(store);

        return store;
    }
}
