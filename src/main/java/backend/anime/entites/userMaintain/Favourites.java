package backend.anime.entites.userMaintain;
import backend.anime.entites.Animes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@Document
public class Favourites {
    @Id
    private ObjectId id;
    private String userName;
    @DBRef
    private Animes anime;
    private Boolean set;
}
