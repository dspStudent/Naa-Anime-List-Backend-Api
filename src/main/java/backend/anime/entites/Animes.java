package backend.anime.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Animes")
public class Animes {
    @Id
    private ObjectId id;
    private Integer animeId;
    private String title;
    private String type;
    private String source;
    private Integer episodes;
    private String status;
    private String airedString;
    private String rating;
    private Integer scoredBy;
    private String premiered;
    private List<String> producer;
    private String licensor;
    private String studio;
    private List<String> genre;
}
