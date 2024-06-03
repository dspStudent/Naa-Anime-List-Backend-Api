package backend.anime.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SlashControll {
    @GetMapping("/")
    public ResponseEntity<String> getSlash(){
        return ResponseEntity.ok(" login Succes full ");
    }
}
