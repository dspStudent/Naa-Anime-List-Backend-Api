package backend.anime.Controller.getStatusOfAnime;
import backend.anime.Service.AnimeStatusService;
import backend.anime.Util.GetUserName;
import backend.anime.entites.userMaintain.AllStatus;
import backend.anime.entites.userMaintain.Favourites;
import backend.anime.exception.exceptionImp.EmptyContentGetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/lock/get")
public class GetAnimeStatusController {

    @Autowired
    GetUserName getUserName;
    @Autowired
    AnimeStatusService animeStatusService;

    @GetMapping("/status")
    public ResponseEntity<AllStatus> getAllStatus(@RequestParam(required = true) String status) throws EmptyContentGetException {
        String userName=getUserName.getUserNameFromToken();
        return ResponseEntity.ok(animeStatusService.getStatus(userName, status));
    }

    @GetMapping("/favourite")
    public ResponseEntity<Favourites> getFavourite() throws EmptyContentGetException {
        String userName=getUserName.getUserNameFromToken();
        return ResponseEntity.ok(animeStatusService.getFavourits(userName));
    }
}
