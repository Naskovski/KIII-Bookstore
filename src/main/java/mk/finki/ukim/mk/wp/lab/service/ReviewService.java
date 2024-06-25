package mk.finki.ukim.mk.wp.lab.service;


import mk.finki.ukim.mk.wp.lab.model.Review;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewService {

    public List<Review> findAllByBookIsbn(String isbn);

    public Review add(Review review);

    public List<Review> findAllByBook_IsbnAndTimestampBetween(String isbn, LocalDateTime from, LocalDateTime to);

}
