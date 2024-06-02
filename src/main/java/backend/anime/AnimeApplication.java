package backend.anime;

import backend.anime.Service.AnimeService;
import backend.anime.entites.Animes;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@SpringBootApplication

public class AnimeApplication {
	public static void main(String[] args) {
		SpringApplication.run(AnimeApplication.class, args);
	}
}
