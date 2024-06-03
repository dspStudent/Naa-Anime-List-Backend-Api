package backend.anime.Controller.reviewController;
import backend.anime.Service.AnimesReviewService;
import backend.anime.Util.GetUserName;
import backend.anime.entites.reviews.Review;
import backend.anime.exception.exceptionImp.EmptyContentGetException;
import backend.anime.exception.exceptionImp.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("lock/review")
public class ReviewController {
    @Autowired
    GetUserName getUserName;
    @Autowired
    AnimesReviewService animesReviewService;
    @PostMapping("/write")
    public ResponseEntity<Review> writeReview(@RequestParam(required = true) String anime,
                                              @RequestParam(required = true) String body){
        String userName=getUserName.getUserNameFromToken();
        return ResponseEntity.ok(animesReviewService.writeReview(anime, userName, body));
    }
    @PutMapping("/edit")
    public ResponseEntity<Review> editReview(
                                             @RequestParam(required = true) String body,
                                             @RequestParam(required = false) String editBody) throws EmptyContentGetException {
        String userName=getUserName.getUserNameFromToken();
        return ResponseEntity.ok(animesReviewService.editReview(userName, body, editBody));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteReview(@RequestParam(required = true) String body) throws UserNotFoundException {
        String userName=getUserName.getUserNameFromToken();
        return ResponseEntity.ok(animesReviewService.deleteReview(userName, body));
    }
}
