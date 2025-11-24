package gitoli.java.projects.com.delight_music_hub.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentRequest {
    @NotBlank
    private String musicClassId;

    @NotNull
    @Min(1)
    private Integer totalSessions;
}
