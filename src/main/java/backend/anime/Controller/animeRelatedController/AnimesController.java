package backend.anime.Controller.animeRelatedController;

import backend.anime.Service.AnimeService;
import backend.anime.entites.Animes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animes")
public class AnimesController {
    @Autowired
    AnimeService animeService;
    @GetMapping("/home")
    public ResponseEntity<List<Animes>> getAnimes(){
        return new ResponseEntity<>(animeService.getAnimesAll(), HttpStatus.OK);
    }
}
