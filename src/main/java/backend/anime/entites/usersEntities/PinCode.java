package backend.anime.entites.usersEntities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class PinCode {
    private String pinCodeNumber;
    private LocalDateTime issuedAt;
    private  LocalDateTime expiration;
}
