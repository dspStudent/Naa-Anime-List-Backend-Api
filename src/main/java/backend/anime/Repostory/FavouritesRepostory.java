package backend.anime.Repostory;

import backend.anime.entites.userMaintain.Favourites;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouritesRepostory extends MongoRepository<Favourites, ObjectId> {
}
