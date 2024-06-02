package backend.anime.Service;

import backend.anime.Repostory.AnimesRepostory;
import backend.anime.Repostory.ImagesRepostory;
import backend.anime.entites.Animes;
import backend.anime.entites.usersEntities.Images;
import backend.anime.exception.exceptionImp.EmptyContentGetException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImp implements ImageService{
    @Autowired
    AnimesRepostory animesRepostory;
    @Autowired
    ImagesRepostory imagesRepostory;
    @Override
    public Images getImageByAnime(String anime) throws EmptyContentGetException {
        Animes animes=animesRepostory.findByTitleIgnoreCase(anime);
        log.info(animes.toString());
        if(animes==null)throw new EmptyContentGetException("No Anime of that name");
//        log.info(""+imagesRepostory.findAll());
        Optional<List<Images>>imagesOptional=imagesRepostory.findByUid(animes.getAnimeId());
//        log.info(imagesOptional.toString());
        if(imagesOptional.isEmpty())throw new EmptyContentGetException("No images found for that anime");
        return imagesOptional.get().getFirst();
    }
}
