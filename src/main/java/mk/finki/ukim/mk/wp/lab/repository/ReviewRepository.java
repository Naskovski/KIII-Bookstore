package mk.finki.ukim.mk.wp.lab.repository;

import mk.finki.ukim.mk.wp.lab.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findAllByBook_Isbn(String isbn);
    public List<Review> findAllByBook_IsbnAndTimestampBetween(String isbn, LocalDateTime from, LocalDateTime to);
}
