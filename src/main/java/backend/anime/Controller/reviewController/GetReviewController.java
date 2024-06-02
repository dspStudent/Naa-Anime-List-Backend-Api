package backend.anime.Controller.reviewController;

import backend.anime.Service.AnimesReviewService;
import backend.anime.Util.GetUserName;
import backend.anime.entites.reviews.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/animes")
public class GetReviewController {
    @Autowired
    GetUserName getUserName;
    @Autowired
    AnimesReviewService animesReviewService;
    @GetMapping("/get/reviews")
    public ResponseEntity<List<Review>> getReviews(@RequestParam(required = true) String anime){
        return ResponseEntity.ok(animesReviewService.getAllAnimesReviews(anime));
    }
}
