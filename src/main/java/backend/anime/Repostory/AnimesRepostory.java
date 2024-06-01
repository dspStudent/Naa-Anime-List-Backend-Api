package backend.anime.Repostory;

import backend.anime.entites.Animes;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimesRepostory extends MongoRepository<Animes, ObjectId> {

    List<Animes> findByTitleContainsIgnoreCase(String name, Pageable paging);

    Animes findByTitle(String anime);

    Animes findByTitleIgnoreCase(String anime);
}
