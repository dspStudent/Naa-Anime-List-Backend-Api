package backend.anime.Controller;
import backend.anime.Service.AnimeStatusService;
import backend.anime.Util.GetUserName;
import backend.anime.entites.userMaintain.AllAnimesStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/lock")
public class AnimeStatusControll {
    @Autowired
    AnimeStatusService animeStatusService;
    @Autowired
    GetUserName getUserName;
    @GetMapping("/getStatus")
    public ResponseEntity<AllAnimesStatus[]> getStatus(){
        return ResponseEntity.ok(AllAnimesStatus.values());
    }
    @PostMapping("/updateStatus")
    public ResponseEntity<String> addToComplete(@RequestParam(required = true) String animes, @RequestParam(required = true) String status){
        String userName=getUserName.getUserNameFromToken();
        return ResponseEntity.ok(animeStatusService.addToUpdate(userName, animes, status));
    }
    @PostMapping("/addToFavourite")
    public ResponseEntity<String> addToFavourite(@RequestParam(required = true) String anime){
        String userName=getUserName.getUserNameFromToken();
        return ResponseEntity.ok(animeStatusService.addToFavourite(anime, userName));
    }
}
