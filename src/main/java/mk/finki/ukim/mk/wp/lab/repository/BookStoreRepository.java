package mk.finki.ukim.mk.wp.lab.repository;

import mk.finki.ukim.mk.wp.lab.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreRepository extends JpaRepository<BookStore, Long> {

}
