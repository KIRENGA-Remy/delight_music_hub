package gitoli.java.projects.com.delight_music_hub.repository;

import gitoli.java.projects.com.delight_music_hub.model.Enrollment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends MongoRepository<Enrollment, String> {
    List<Enrollment> findByUserId(String userId);
    List<Enrollment> findByMusicClassId(String musicClassId);
}
