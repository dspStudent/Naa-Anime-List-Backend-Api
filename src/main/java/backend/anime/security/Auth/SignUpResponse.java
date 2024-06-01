package backend.anime.security.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpResponse {
    private String message;
    private String url;
    @Override
    public String toString(){
        return message+" \n "+url;
    }
}
