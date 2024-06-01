package backend.anime.Service;
import backend.anime.Repostory.AllStatusRepostory;
import backend.anime.Repostory.AnimesRepostory;
import backend.anime.entites.Animes;
import backend.anime.entites.userMaintain.AllAnimesStatus;
import backend.anime.entites.userMaintain.AllStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimeStatusServiceImp implements AnimeStatusService{
    @Autowired
    AnimesRepostory animesRepostory;
    @Autowired
    AllStatusRepostory allStatusRepostory;
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

}
