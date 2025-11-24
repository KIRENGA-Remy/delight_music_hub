package gitoli.java.projects.com.delight_music_hub.controller;

import gitoli.java.projects.com.delight_music_hub.dto.BookingRequest;
import gitoli.java.projects.com.delight_music_hub.model.Booking;
import gitoli.java.projects.com.delight_music_hub.repository.BookingRepository;
import gitoli.java.projects.com.delight_music_hub.repository.ProducerRepository;
import gitoli.java.projects.com.delight_music_hub.repository.StudioRoomRepository;
import gitoli.java.projects.com.delight_music_hub.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@Tag(name = "Bookings", description = "Studio booking management")
@SecurityRequirement(name = "Bearer Authentication")
public class BookingController {
    private final BookingRepository bookingRepository;
    private final StudioRoomRepository studioRoomRepository;
    private final ProducerRepository producerRepository;

    @PostMapping
    @Operation(summary = "Create new booking")
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingRequest bookingRequest, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        var studioRoom = studioRoomRepository.findById(bookingRequest.getStudioRoomId())
                .orElseThrow(() -> new RuntimeException("Studio room not found"));

        Double totalAmount = studioRoom.getHourlyRate() * bookingRequest.getDurationHours();

        if(bookingRequest.getProducerId() != null){
            var producer = producerRepository.findById(bookingRequest.getProducerId())
                    .orElseThrow(() -> new RuntimeException("Producer not found"));
            totalAmount += producer.getHourlyRate() * bookingRequest.getDurationHours();
        }

        Booking booking = new Booking();
        booking.setUserId(userDetails.getId());
        booking.setStudioRoomId(bookingRequest.getStudioRoomId());
        booking.setProducerId(bookingRequest.getProducerId());
        booking.setBookingDate(bookingRequest.getBookingDate());
        booking.setDurationHours(bookingRequest.getDurationHours());
        booking.setServiceType(bookingRequest.getServiceType());
        booking.setProjectDescription(bookingRequest.getProjectDescription());
        booking.setStatus("PENDING");
        booking.setTotalAmount(totalAmount);

        Booking saved = bookingRepository.save(booking);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    @Operation(summary = "Get user bookings")
    public ResponseEntity<List<Booking>> getUserBookings(Authentication auth){
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        return ResponseEntity.ok(bookingRepository.findByUserId(userDetails.getId()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get booking by ID")
    public ResponseEntity<Booking> getBookingById(@PathVariable String id){
        return bookingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
