package backend.anime.Service;

import backend.anime.entites.usersEntities.Images;
import backend.anime.exception.exceptionImp.EmptyContentGetException;
import org.springframework.stereotype.Service;

@Service
public interface ImageService {
    Images getImageByAnime(String anime) throws EmptyContentGetException;
}
