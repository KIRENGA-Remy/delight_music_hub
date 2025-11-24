package gitoli.java.projects.com.delight_music_hub.config;

import gitoli.java.projects.com.delight_music_hub.model.MusicClass;
import gitoli.java.projects.com.delight_music_hub.model.StudioRoom;
import gitoli.java.projects.com.delight_music_hub.repository.ArtistRepository;
import gitoli.java.projects.com.delight_music_hub.repository.MusicClassRepository;
import gitoli.java.projects.com.delight_music_hub.repository.ProducerRepository;
import gitoli.java.projects.com.delight_music_hub.repository.StudioRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            StudioRoomRepository studioRoomRepository,
            ProducerRepository producerRepository,
            ArtistRepository artistRepository,
            MusicClassRepository musicClassRepository) {
        return args -> {
            // Initialize Studio Rooms
            if (studioRoomRepository.count() == 0) {
                studioRoomRepository.saveAll(Arrays.asList(
                        createStudioRoom("Studio A - Premium",
                                "Professional recording studio with Neumann U87, SSL console, acoustic treatment",
                                50000.0),
                        createStudioRoom("Studio B - Standard",
                                "Great for vocals and acoustic sessions with Shure SM7B, Focusrite interface",
                                30000.0),
                        createStudioRoom("Production Suite",
                                "Beat production and mixing suite with Yamaha HS8, MIDI controllers, FL Studio",
                                40000.0)
                ));
            }

            // Initialize Music Classes
            if (musicClassRepository.count() == 0) {
                musicClassRepository.saveAll(Arrays.asList(
                        createMusicClass("Beginner Guitar", "Guitar", "Beginner",
                                "Learn guitar fundamentals, chords, and basic songs", 15000.0),
                        createMusicClass("Advanced Piano", "Piano", "Advanced",
                                "Master complex pieces and improvisation techniques", 25000.0),
                        createMusicClass("Vocal Training", "Vocals", "All Levels",
                                "Develop your voice with breathing, pitch, and performance skills", 20000.0),
                        createMusicClass("Drums Basics", "Drums", "Beginner",
                                "Learn rhythm, beats, and coordination on acoustic drums", 18000.0)
                ));
            }
        };
    }

    private StudioRoom createStudioRoom(String name, String description, Double rate) {
        StudioRoom room = new StudioRoom();
        room.setName(name);
        room.setDescription(description);
        room.setEquipment(description);
        room.setHourlyRate(rate);
        room.setCapacity(4);
        room.setAvailable(true);
        return room;
    }

    private MusicClass createMusicClass(String name, String instrument, String level,
                                        String description, Double price) {
        MusicClass musicClass = new MusicClass();
        musicClass.setName(name);
        musicClass.setInstrument(instrument);
        musicClass.setLevel(level);
        musicClass.setDescription(description);
        musicClass.setPricePerSession(price);
        musicClass.setDurationMinutes(60);
        musicClass.setSchedule("Flexible - Contact us");
        musicClass.setMode("On-site & Online");
        musicClass.setActive(true);
        return musicClass;
    }
}