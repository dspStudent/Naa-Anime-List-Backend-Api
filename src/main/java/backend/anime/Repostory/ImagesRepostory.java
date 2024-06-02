package backend.anime.Repostory;

import backend.anime.entites.usersEntities.Images;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImagesRepostory extends MongoRepository<Images, ObjectId> {
    Optional<List<Images>> findByUid(Integer animeId);
}
