package gitoli.java.projects.com.delight_music_hub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "music_classes")
public class MusicClass {
    @Id
    private String id;
    private String name;
    private String instrument;
    private String level;
    private String description;
    private String instructorId;
    private String instructorName;
    private Double pricePerSession;
    private Integer durationMinutes;
    private String schedule;
    private String mode;
    private String image;
    private boolean isActive = true;

    @CreatedDate
    private LocalDateTime createdAt;
}
