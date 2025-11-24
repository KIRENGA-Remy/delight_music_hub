package gitoli.java.projects.com.delight_music_hub.controller;

import gitoli.java.projects.com.delight_music_hub.model.MusicClass;
import gitoli.java.projects.com.delight_music_hub.repository.MusicClassRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/music-classes")
@RequiredArgsConstructor
@Tag(name = "Music classes", description = "Music class management")
public class MusicClassController {
    private final MusicClassRepository musicClassRepository;

    @GetMapping
    @Operation(summary = "Get all music classes")
    public ResponseEntity<List<MusicClass>> getAllMusicClasses(){
        return ResponseEntity.ok(musicClassRepository.findAll());
    }

    @GetMapping("/active")
    @Operation(summary = "Get active music classes")
    public ResponseEntity<List<MusicClass>> getActiveMusicClasses(){
        return ResponseEntity.ok(musicClassRepository.findByIsActive(true));
    }

    @GetMapping("/instrument/{instrument}")
    @Operation(summary = "Get classes by instrument")
    public ResponseEntity<List<MusicClass>> getClassesByInstrument(@PathVariable String instrument) {
        return ResponseEntity.ok(musicClassRepository.findByInstrument(instrument));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get music class by ID")
    public ResponseEntity<MusicClass> getClassById(@PathVariable String id){
        return musicClassRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
