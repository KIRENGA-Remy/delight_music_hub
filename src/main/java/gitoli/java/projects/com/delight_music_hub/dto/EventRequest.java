package gitoli.java.projects.com.delight_music_hub.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventRequest {
    @NotBlank
    private String artistId;

    @NotBlank
    private String eventName;

    @NotBlank
    private String eventType;

    @NotNull
    private LocalDateTime eventDate;

    @NotBlank
    private String venue;

    private String description;

    @NotNull
    @Min(0)
    private Double budget;
}
