package backend.anime.security.oathtwo;
import backend.anime.Repostory.UserRepostory;
import backend.anime.entites.usersEntities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Objects;


@Component
@Slf4j
public class Oauth2SucsessHandlerimpl implements AuthenticationSuccessHandler {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    UserRepostory userRepostory;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException{
        DefaultOAuth2User defaultOAuth2User=(DefaultOAuth2User) authentication.getPrincipal();
        String email= Objects.requireNonNull(defaultOAuth2User.getAttribute("email")).toString();
//        String firstName=defaultOAuth2User.getName();
        String firstName= Objects.requireNonNull(defaultOAuth2User.getAttribute("given_name")).toString();
        String secondName= Objects.requireNonNull(defaultOAuth2User.getAttribute("family_name")).toString();
        log.info(defaultOAuth2User.getAttributes().toString());
        String picture= Objects.requireNonNull(defaultOAuth2User.getAttribute("picture")).toString();
        var useDetails=userRepostory.findByEmail(email);
        if(!useDetails.isPresent()) {
            var user = User
                    .builder()
                    .email(email)
                    .firstName(firstName)
                    .secondName(secondName)
                    .pictureUrl(picture)
                    .enabled(true)
                    .build();
            userRepostory.save(user);
        }
        else{
            Query query=new Query(Criteria.where("email").is(email));
            Update update=new Update().set("pictureUrl", picture).set("enabled", true);
            mongoTemplate.updateFirst(query, update, User.class);
        }
//        String funnyMessage = "Congratulations! You've been hacked";
//        response.getWriter().write(funnyMessage);
//        response.getWriter().flush();
        response.sendRedirect("/animes/home");
    }
}
