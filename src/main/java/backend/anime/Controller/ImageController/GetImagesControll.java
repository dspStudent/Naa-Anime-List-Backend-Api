package backend.anime.Controller.ImageController;
import backend.anime.Service.ImageService;
import backend.anime.entites.usersEntities.Images;
import backend.anime.exception.exceptionImp.EmptyContentGetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/animes")
public class GetImagesControll {
    @Autowired
    ImageService imageService;
    @GetMapping("/image")
    public ResponseEntity<Images> getImages(@RequestParam(required = true) String anime) throws EmptyContentGetException {
        return ResponseEntity.ok(imageService.getImageByAnime(anime));
    }
}
