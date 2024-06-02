package backend.anime.entites.reviews;
import backend.anime.entites.Animes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Review {
    @Id
    private ObjectId id;
    @DBRef
    private Animes animes;
    private String userName;
    private String body;
}
