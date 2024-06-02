package backend.anime.Repostory;

import backend.anime.entites.userMaintain.Favourites;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavouritesRepostory extends MongoRepository<Favourites, ObjectId> {
    Optional<Favourites> findByAnimeAndUserName(String anime, String userName);

    Optional<Favourites> findByUserNameAndSet(String userName, boolean set);
}
