package backend.anime.Service;
import backend.anime.Repostory.AnimesRepostory;
import backend.anime.Repostory.UserRepostory;
import backend.anime.entites.Animes;
import backend.anime.entites.usersEntities.User;
import backend.anime.exception.exceptionImp.EmptyContentGetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AnimeServiceimpl implements AnimeService{
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    AnimesRepostory animesRepostory;
    @Autowired
    UserRepostory userRepostory;


    @Override
    public List<Animes> getByFilter(List<String> geners, List<String> type, List<String> source,
                                    Integer minEpisodes, Integer maxEpisods, List<String> status, List<String> rating,
                                    List<String> studio, List<String>genersToBeRemoved, Integer page, Integer pageSize)
    throws EmptyContentGetException{
        List<Criteria> criteria = new ArrayList<>();
        Integer skip=(page-1)*pageSize;
        if (geners != null && !geners.isEmpty()) {
            List<Criteria> genreCriteriaList = new ArrayList<>();
            for (String genre : geners) {
                genreCriteriaList.add(Criteria.where("genre").regex(genre, "i"));
            }
            // Combine all genre criteria with logical AND
            Criteria genreCriteria = new Criteria().andOperator(genreCriteriaList.toArray(new Criteria[0]));
            criteria.add(genreCriteria);
        }
        if(genersToBeRemoved !=null && !genersToBeRemoved.isEmpty()){
            List<Criteria>removeCriteria=new ArrayList<>();
            for(String generRemove:genersToBeRemoved){
                removeCriteria.add(Criteria.where("genre").not().regex(generRemove, "i"));
            }
            criteria.add(new Criteria().andOperator(removeCriteria.toArray(new Criteria[0])));
        }
        if (type != null && !type.isEmpty()) {
            criteria.add(Criteria.where("type").in(type));
        }
        if (source != null && !source.isEmpty()) {
            criteria.add(Criteria.where("source").in(source));
        }
        if (minEpisodes != null) {
            criteria.add(Criteria.where("episodes").gte(minEpisodes));
        }
        if (maxEpisods != null) {
            criteria.add(Criteria.where("episodes").lte(maxEpisods));
        }
        if (status != null && !status.isEmpty()) {
            criteria.add(Criteria.where("status").in(status));
        }
        if (rating != null && !rating.isEmpty()) {
            criteria.add(Criteria.where("rating").in(rating));
        }
        if (studio != null && !studio.isEmpty()) {
            criteria.add(Criteria.where("studio").in(studio));
        }
//        Sort sort=Sort.by(Sort.Direction.DESC, "scoredBy");
        Query query = new Query(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        query.skip(skip).limit(pageSize);
        List<Animes>toReturn= mongoTemplate.find(query, Animes.class);
        if( Objects.isNull(toReturn) || toReturn.isEmpty())throw new EmptyContentGetException("There is no data for your request");
        return toReturn;
    }

    @Override
    public List<Animes> getNameByName(String name, Integer page, Integer pageSize) throws EmptyContentGetException {
        Pageable paging= PageRequest.of(page, pageSize);
        List<Animes> animes=animesRepostory.findByTitleContainsIgnoreCase(name, paging);
        if(animes.isEmpty())throw new EmptyContentGetException("There is no data for your request");
        return animes;
    }

    @Override
    public User save(User user) {
        return userRepostory.save(user);
    }

    @Override
    public List<Animes> getAnimesAll(Integer page, Integer pageSize) {
        Pageable paging= PageRequest.of(page, pageSize);
        return animesRepostory.findAll(paging).getContent();
    }
}
