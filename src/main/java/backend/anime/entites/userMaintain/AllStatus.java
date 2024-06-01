package backend.anime.entites.userMaintain;

import backend.anime.entites.Animes;
import backend.anime.entites.usersEntities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class AllStatus {
    @Id
    private ObjectId id;
    @DBRef
    private Animes animes;
    private String userName;
    private String allAnimesStatus;

    public AllStatus(Animes animes, String userName, AllAnimesStatus allAnimesStatus) {
    }
}
