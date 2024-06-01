package backend.anime.entites.usersEntities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModal {
    @Id
    private ObjectId id;
    @NotBlank(message = "first name should not be empty")
    private String firstName;

    @NotBlank(message = "second name should not be empty")
    private String secondName;

    private String email;

    @Length(min=5, message = "min 5 characters should be there")
    private String password;

    @Length(min=5, message = "min 5 characters should be there")
    private String confirmPassword;


}
