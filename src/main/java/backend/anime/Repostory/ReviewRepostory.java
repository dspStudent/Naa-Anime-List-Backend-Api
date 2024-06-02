package backend.anime.Repostory;

import backend.anime.entites.Animes;
import backend.anime.entites.reviews.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepostory extends MongoRepository<Review, ObjectId> {

    List<Review> findAllByAnimes(Animes animes);

    Review findAllByUserNameAndBody(String userName, String body);
}
