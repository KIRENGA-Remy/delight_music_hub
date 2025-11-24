package gitoli.java.projects.com.delight_music_hub.controller;

import gitoli.java.projects.com.delight_music_hub.model.Producer;
import gitoli.java.projects.com.delight_music_hub.repository.ProducerRepository;
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
@RequestMapping("/api/producers")
@RequiredArgsConstructor
@Tag(name = "Producers", description = "Music producer management")
public class ProducerController {
    private final ProducerRepository producerRepository;

    @GetMapping("/available")
    @Operation(summary = "Get available producers")
    public ResponseEntity<List<Producer>> getAllProducers(){
        return ResponseEntity.ok(producerRepository.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get producer by ID")
    public ResponseEntity<Producer> getProducerById(@PathVariable String id){
        return producerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
