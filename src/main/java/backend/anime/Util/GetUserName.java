package backend.anime.Util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetUserName {
    public String getUserNameFromToken(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Object ob=authentication.getPrincipal();
        String user="";
        if(ob instanceof UserDetails){
            log.info(((UserDetails) ob).getUsername());
            user=((UserDetails) ob).getUsername();
        }
        else if(ob instanceof DefaultOAuth2User) {
            user = ((DefaultOAuth2User) ob).getAttribute("email");
        }
        else user=ob.toString();
        return user;
    }
}
