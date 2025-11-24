package gitoli.java.projects.com.delight_music_hub.repository;

import gitoli.java.projects.com.delight_music_hub.model.Producer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProducerRepository extends MongoRepository<Producer, String> {
    List<Producer> findByIsAvailable(boolean isAvailable);
    Optional<Producer> findByUserId(String userId);
}
