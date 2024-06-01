package backend.anime.Repostory;

import backend.anime.entites.usersEntities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepostory extends MongoRepository<User, ObjectId> {
    Optional<UserDetails> findByEmail(String mail);
}
