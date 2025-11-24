package gitoli.java.projects.com.delight_music_hub.repository;

import gitoli.java.projects.com.delight_music_hub.model.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends MongoRepository<Artist, String> {
    List<Artist> findByIsAvailableForEvents(boolean isAvailableForEvents);
    Optional<Artist> findByUserId(String userId);
}
