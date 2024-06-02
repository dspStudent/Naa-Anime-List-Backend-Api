package backend.anime;

import backend.anime.email.EmailSevice;
import backend.anime.entites.usersEntities.User;
import backend.anime.entites.usersEntities.UserModal;
import backend.anime.security.Auth.AuthService;
import backend.anime.security.Auth.SignUpResponse;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AnimeApplicationTests {


	@Test
	void contextLoads() {
	}


}
