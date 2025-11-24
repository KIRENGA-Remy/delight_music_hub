package gitoli.java.projects.com.delight_music_hub.controller;

import gitoli.java.projects.com.delight_music_hub.model.Artist;
import gitoli.java.projects.com.delight_music_hub.repository.ArtistRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/artists")
@RequiredArgsConstructor
@Tag(name = "Artists", description = "Artist management")
public class ArtistController {
    private final ArtistRepository artistRepository;

    @GetMapping
    @Operation(summary = "Get all artists")
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok(artistRepository.findAll());
    }

    @GetMapping("/available")
    @Operation(summary = "Get artists available for events")
    public ResponseEntity<List<Artist>> getAvailableArtists() {
        return ResponseEntity.ok(artistRepository.findByIsAvailableForEvents(true));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get artist by ID")
    public ResponseEntity<Artist> getArtistById(@PathVariable String id){
        return artistRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
