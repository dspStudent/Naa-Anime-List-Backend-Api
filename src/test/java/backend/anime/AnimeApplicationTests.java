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

	@Autowired
	EmailSevice emailSevice;
	@MockBean
	private AuthService authService;

	@Test
	public void signUpUserTest_success() {
		UserModal user = UserModal.builder()
				.firstName("Dev")
				.secondName("Ranga")
				.email("devisriprasad948@gmail.com")
				.password("Devi")
				.confirmPassword("Devi")
				.build();

		User expectedUser = User.builder() // Assuming User class exists
				 // Set an ID if applicable
				.firstName("Dev")
				.secondName("Ranga")
				.email("devisriprasad948@gmail.com")
				.password("Devi")
				.build();

		Mockito.when(authService.signUpUser(user)).getMock();

		authService.signUpUser(user);

		// Verify that authService.signUpUser was called with the correct user
		Mockito.verify(authService).signUpUser(user);
	}

	@Test
	public void mailSendTo() throws MessagingException {
		emailSevice.mailSendTo("devisriprasad948@gmail.com", "verfiy", "aa");
	}
}
