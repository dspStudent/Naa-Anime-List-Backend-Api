package backend.anime.entites.userMaintain;
import backend.anime.entites.Animes;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document
public class Favourites {
    @Id
    private ObjectId id;
    private String userName;
    private Animes anime;
}
