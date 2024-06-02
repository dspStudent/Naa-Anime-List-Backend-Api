package backend.anime.Service;
import backend.anime.Repostory.AllStatusRepostory;
import backend.anime.Repostory.AnimesRepostory;
import backend.anime.Repostory.FavouritesRepostory;
import backend.anime.entites.Animes;
import backend.anime.entites.userMaintain.AllAnimesStatus;
import backend.anime.entites.userMaintain.AllStatus;
import backend.anime.entites.userMaintain.Favourites;
import backend.anime.exception.exceptionImp.EmptyContentGetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimeStatusServiceImp implements AnimeStatusService{
    @Autowired
    AnimesRepostory animesRepostory;
    @Autowired
    AllStatusRepostory allStatusRepostory;
    @Autowired
    FavouritesRepostory favouritesRepostory;
    @Override
    public String addToUpdate(String userName, String anime, String status) {
        Animes animes=animesRepostory.findByTitleIgnoreCase(anime);
        Optional<AllStatus> allStatusOptional=allStatusRepostory.findByAnimesAndUserName(animes, userName);
        if(allStatusOptional.isEmpty()){
            allStatusRepostory.save(AllStatus.builder().animes(animes).allAnimesStatus(status).userName(userName).build());
            return "sucess";
        }
        AllStatus allStatus=allStatusOptional.get();
        if(allStatus.getAllAnimesStatus()==null || allStatus.getAllAnimesStatus().equals(AllAnimesStatus.NONE.toString())){
            allStatus.setAllAnimesStatus(status);
            allStatusRepostory.save(allStatus);
            return "sucsess";
        }
        if(allStatus.getAllAnimesStatus().equalsIgnoreCase(status)) {
            allStatus.setAllAnimesStatus(AllAnimesStatus.NONE.toString());
            allStatusRepostory.save(allStatus);
            return "Sucsss";
        }
        allStatus.setAllAnimesStatus(status);
        allStatusRepostory.save(allStatus);
        return "sucess";
    }

    @Override
    public String addToFavourite(String anime, String userName) {
        Optional<Favourites> favourites=favouritesRepostory.findByAnimeAndUserName(anime, userName);
        Animes animes=animesRepostory.findByTitleIgnoreCase(anime);
        if(favourites.isEmpty()){
            favouritesRepostory.save(Favourites.builder().anime(animes).userName(userName).set(true).build());
            return "added sucessfully";
        }
        Favourites favourite=favourites.get();
        favourite.setSet(false);
        favouritesRepostory.save(favourite);
        return "added sucessfully";
    }

    @Override
    public AllStatus getStatus(String userName, String status) throws EmptyContentGetException {
        Optional<AllStatus>allStatusOptional=allStatusRepostory.findByAllAnimesStatusIgnoreCaseAndUserName(status, userName);
        if(allStatusOptional.isEmpty())throw new EmptyContentGetException("you have not selectd any thing");
        return allStatusOptional.get();
    }

    @Override
    public Favourites getFavourits(String userName) throws EmptyContentGetException {
        Optional<Favourites>allFavouriteOptional=favouritesRepostory.findByUserNameAndSet(userName, true);
        if(allFavouriteOptional.isEmpty())throw new EmptyContentGetException("you have not selectd any thing");
        return allFavouriteOptional.get();
    }

}
