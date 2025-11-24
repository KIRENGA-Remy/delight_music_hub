package gitoli.java.projects.com.delight_music_hub.repository;

import gitoli.java.projects.com.delight_music_hub.model.StudioRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudioRoomRepository  extends MongoRepository<StudioRoom, String> {
    List<StudioRoom> findByIsAvailable(boolean isAvailable);
}
