package gitoli.java.projects.com.delight_music_hub.repository;

import gitoli.java.projects.com.delight_music_hub.model.MusicClass;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicClassRepository extends MongoRepository<MusicClass, String> {
    List<MusicClass> findByIsActive(boolean isActive);
    List<MusicClass> findByInstrument(String instrument);
}
