package gitoli.java.projects.com.delight_music_hub.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BookingRequest {
    @NotNull
    private String studioRoomId;
    private String producerId;

    @NotNull
    private LocalDateTime bookingDate;

    @NotNull
    @Min(1)
    private Integer durationHours;

    @NotBlank
    private String serviceType;

    private String projectDescription;
}
