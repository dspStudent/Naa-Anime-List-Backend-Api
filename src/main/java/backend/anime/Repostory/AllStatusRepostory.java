package backend.anime.Repostory;

import backend.anime.entites.Animes;
import backend.anime.entites.userMaintain.AllStatus;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AllStatusRepostory extends MongoRepository<AllStatus, ObjectId> {

    Optional<AllStatus> findByAnimesAndUserName(Animes animes, String userName);
}
