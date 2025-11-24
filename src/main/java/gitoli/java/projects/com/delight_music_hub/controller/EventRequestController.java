package gitoli.java.projects.com.delight_music_hub.controller;

import gitoli.java.projects.com.delight_music_hub.dto.EventRequestDTO;
import gitoli.java.projects.com.delight_music_hub.model.EventRequest;
import gitoli.java.projects.com.delight_music_hub.repository.EventRequestRepository;
import gitoli.java.projects.com.delight_music_hub.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-requests")
@RequiredArgsConstructor
@Tag(name = "Event Requests", description = "Artist event booking")
@SecurityRequirement(name = "Bearer Authentication")
public class EventRequestController {
    private final EventRequestRepository eventRequestRepository;

    @PostMapping
    @Operation(summary = "Create event request")
    public ResponseEntity<?> createEventRequest(@Valid @RequestBody EventRequestDTO eventRequestDTO, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        EventRequest eventRequest = new EventRequest();
        eventRequest.setUserId(userDetails.getId());
        eventRequest.setArtistId(eventRequestDTO.getArtistId());
        eventRequest.setEventName(eventRequestDTO.getEventName());
        eventRequest.setEventType(eventRequestDTO.getEventType());
        eventRequest.setEventDate(eventRequestDTO.getEventDate());
        eventRequest.setVenue(eventRequestDTO.getVenue());
        eventRequest.setDescription(eventRequestDTO.getDescription());
        eventRequest.setBudget(eventRequestDTO.getBudget());
        eventRequest.setStatus("PENDING");

        EventRequest savedEventRequest = eventRequestRepository.save(eventRequest);
        return ResponseEntity.ok(savedEventRequest);
    }

    @GetMapping
    @Operation(summary = "Get user event requests")
    public ResponseEntity<List<EventRequest>> getUserEventRequests(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(eventRequestRepository.findByUserId(userDetails.getId()));
    }
}
