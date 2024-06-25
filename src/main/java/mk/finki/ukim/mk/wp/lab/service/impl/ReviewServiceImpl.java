package mk.finki.ukim.mk.wp.lab.service.impl;

import mk.finki.ukim.mk.wp.lab.model.Review;
import mk.finki.ukim.mk.wp.lab.repository.ReviewRepository;
import mk.finki.ukim.mk.wp.lab.service.BookService;
import mk.finki.ukim.mk.wp.lab.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAllByBookIsbn(String isbn) {
        return reviewRepository.findAllByBook_Isbn(isbn);
    }

    @Override
    public Review add(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findAllByBook_IsbnAndTimestampBetween(String isbn, LocalDateTime from, LocalDateTime to) {
        return reviewRepository.findAllByBook_IsbnAndTimestampBetween(isbn, from, to);
    }
}
