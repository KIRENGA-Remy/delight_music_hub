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
@Document(collection = "studio_rooms")
public class StudioRoom {
    @Id
    private String id;
    private String name;
    private String description;
    private String equipment;
    private Double hourlyRate;
    private Integer capacity;
    private String image;
    private boolean isAvailable = true;

    @CreatedDate
    private LocalDateTime createdAt;
}
