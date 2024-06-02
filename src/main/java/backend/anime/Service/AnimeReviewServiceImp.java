package backend.anime.Service;
import backend.anime.Repostory.AnimesRepostory;
import backend.anime.Repostory.ReviewRepostory;
import backend.anime.entites.Animes;
import backend.anime.entites.reviews.Review;
import backend.anime.exception.exceptionImp.EmptyContentGetException;
import backend.anime.exception.exceptionImp.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeReviewServiceImp implements AnimesReviewService{
    @Autowired
    ReviewRepostory reviewRepostory;
    @Autowired
    AnimesRepostory animesRepostory;
    @Override
    public Review writeReview(String anime, String userName, String body) {
        Animes animes=animesRepostory.findByTitleIgnoreCase(anime);
        var review=Review.builder().animes(animes).userName(userName).body(body).build();
        reviewRepostory.save(review);
        return review;
    }

    @Override
    public List<Review> getAllAnimesReviews(String anime) {
        Animes animes=animesRepostory.findByTitleIgnoreCase(anime);
        return reviewRepostory.findAllByAnimes(animes);
    }

    @Override
    public Review editReview(String userName, String body, String editBody) throws EmptyContentGetException {
        Review review=reviewRepostory.findAllByUserNameAndBody(userName, body);
        if(editBody==null)throw new EmptyContentGetException("user must enter to edit review");
        review.setBody(editBody);
        reviewRepostory.save(review);
        return review;
    }

    @Override
    public String deleteReview(String userName, String body) throws UserNotFoundException {
        Review review=reviewRepostory.findAllByUserNameAndBody(userName, body);
        if(review==null)throw new UserNotFoundException("the field is not present");
        reviewRepostory.delete(review);
        return "deleted sucessfully";
    }
}
