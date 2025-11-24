package gitoli.java.projects.com.delight_music_hub.repository;

import gitoli.java.projects.com.delight_music_hub.model.EventRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRequestRepository extends MongoRepository<EventRequest, String> {
    List<EventRequest> findByUserId(String userId);
    List<EventRequest> findByArtistId(String artistId);
}
