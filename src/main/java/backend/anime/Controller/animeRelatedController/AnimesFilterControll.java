package backend.anime.Controller.animeRelatedController;

import backend.anime.Repostory.AllStatusRepostory;
import backend.anime.Service.AnimeService;
import backend.anime.entites.Animes;
import backend.anime.entites.userMaintain.AllStatus;
import backend.anime.exception.exceptionImp.EmptyContentGetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/animes")
public class AnimesFilterControll {
    @Autowired
    AnimeService animeService;
    @GetMapping("/animename/{name}")
    public ResponseEntity<Animes> getByName(@PathVariable("name") String name)
            throws EmptyContentGetException {
        Animes animes=animeService.getAnimeByName(name);
        if(animes==null)throw new EmptyContentGetException("no anime");
        return new ResponseEntity<>(animes, HttpStatus.OK);
    }
    @GetMapping("/animesname/{name}")
    public ResponseEntity<List<Animes>> getByName(@PathVariable("name") String name,
                                                  @RequestParam(defaultValue = "1", required = false)Integer page,
                                                  @RequestParam(defaultValue = "10", required = false) Integer pageSize
    ) throws EmptyContentGetException {
        List<Animes>animes=new ArrayList<>();
        Animes animes1=animeService.getAnimeByName(name);
        if(animes1!=null)animes.add(animes1);
        animes.addAll(animeService.getNameByName(name, page, pageSize));
        return new ResponseEntity<>(animes, HttpStatus.OK);
    }

    @GetMapping("/filter")
    @ResponseBody
    public ResponseEntity<List<Animes>> getByFilter (
            @RequestParam(required = false) List<String> geners,
            @RequestParam(required = false) List<String> type,
            @RequestParam(required = false) List<String> source,
            @RequestParam(required = false) Integer minEpisodes,
            @RequestParam(required = false) Integer maxEpisods,
            @RequestParam(required = false) List<String> status,
            @RequestParam(required = false) List<String> rating,
            @RequestParam(required = false) List<String> studio,
            @RequestParam(required = false) List<String> genersToBeRemoved,
            @RequestParam(defaultValue = "1", required=false) Integer page,
            @RequestParam(defaultValue = "10", required = false)Integer pageSize
    )throws EmptyContentGetException {
        return new ResponseEntity<>(animeService.getByFilter(geners, type, source, minEpisodes, maxEpisods, status, rating, studio, genersToBeRemoved, page, pageSize), HttpStatus.OK);
    }
}