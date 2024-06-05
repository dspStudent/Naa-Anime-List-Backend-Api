package backend.anime.Controller.animeRelatedController;

import backend.anime.Service.AnimeService;
import backend.anime.entites.Animes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/animes")
public class AnimesController {
    @Autowired
    AnimeService animeService;
    @GetMapping("/home")
    public ResponseEntity<List<Animes>> getAnimes(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam( defaultValue = "10", required = false) Integer pageSize
    ){
        return new ResponseEntity<>(animeService.getAnimesAll(page, pageSize), HttpStatus.OK);
    }
}
