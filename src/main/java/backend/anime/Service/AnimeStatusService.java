package backend.anime.Service;

import org.springframework.stereotype.Service;

@Service
public interface AnimeStatusService {
    String addToUpdate(String userName, String anime, String status);
}
