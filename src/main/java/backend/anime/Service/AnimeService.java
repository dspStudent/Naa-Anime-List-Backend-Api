package backend.anime.Service;

import backend.anime.entites.Animes;
import backend.anime.entites.usersEntities.User;
import backend.anime.exception.exceptionImp.EmptyContentGetException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnimeService {


    List<Animes> getByFilter(List<String> geners, List<String> type, List<String> source, Integer minEpisodes, Integer maxEpisods, List<String> status, List<String> rating, List<String> studio, List<String>genersToBeRemoved, Integer page, Integer pageSize) throws EmptyContentGetException;

    List<Animes> getNameByName(String name, Integer page, Integer pageSize) throws EmptyContentGetException;

    User save(User user);

    List<Animes> getAnimesAll(Integer page, Integer pageSize);
}
