package backend.anime.Service;

import backend.anime.entites.reviews.Review;
import backend.anime.exception.exceptionImp.EmptyContentGetException;
import backend.anime.exception.exceptionImp.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnimesReviewService {
    Review writeReview(String anime, String userName, String body);

    List<Review> getAllAnimesReviews(String anime);

    Review editReview(String userName, String body, String editBody) throws EmptyContentGetException;

    String deleteReview(String userName, String body) throws UserNotFoundException;
}
