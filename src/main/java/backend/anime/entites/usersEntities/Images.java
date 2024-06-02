package backend.anime.entites.usersEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Images")
public class Images {
    @Id
    private ObjectId id;
    private Integer uid;
    private String synopsis;
    private String imgUrl;
}
