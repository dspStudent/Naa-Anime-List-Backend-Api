package backend.anime.Service;

import backend.anime.entites.userMaintain.AllStatus;
import backend.anime.entites.userMaintain.Favourites;
import backend.anime.exception.exceptionImp.EmptyContentGetException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AnimeStatusService {
    String addToUpdate(String userName, String anime, String status);

    String addToFavourite(String anime, String userName);

    AllStatus getStatus(String userName, String status) throws EmptyContentGetException;

    Favourites getFavourits(String userName) throws EmptyContentGetException;
}
