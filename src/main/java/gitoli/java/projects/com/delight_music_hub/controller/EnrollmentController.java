package gitoli.java.projects.com.delight_music_hub.controller;

import gitoli.java.projects.com.delight_music_hub.dto.EnrollmentRequest;
import gitoli.java.projects.com.delight_music_hub.model.Enrollment;
import gitoli.java.projects.com.delight_music_hub.repository.EnrollmentRepository;
import gitoli.java.projects.com.delight_music_hub.repository.MusicClassRepository;
import gitoli.java.projects.com.delight_music_hub.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@Tag(name = "Enrollments", description = "Music class enrollment")
@SecurityRequirement(name = "Bearer Authentication")
public class EnrollmentController {
    private final EnrollmentRepository enrollmentRepository;
    private final MusicClassRepository musicClassRepository;

    @PostMapping
    @Operation(summary = "Enroll in music class")
    public ResponseEntity<?> enrollInClass(@Valid @RequestBody EnrollmentRequest request,
                                           Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        var musicClass = musicClassRepository.findById(request.getMusicClassId())
                .orElseThrow(() -> new RuntimeException("Music class not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setUserId(userDetails.getId());
        enrollment.setMusicClassId(request.getMusicClassId());
        enrollment.setEnrollmentDate(LocalDateTime.now());
        enrollment.setStatus("ACTIVE");
        enrollment.setTotalSessions(request.getTotalSessions());
        enrollment.setSessionsCompleted(0);

        Enrollment saved = enrollmentRepository.save(enrollment);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    @Operation(summary = "Get user enrollments")
    public ResponseEntity<List<Enrollment>> getUserEnrollments(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(enrollmentRepository.findByUserId(userDetails.getId()));
    }
}