package gitoli.java.projects.com.delight_music_hub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "event_requests")
public class EventRequest {
    @Id
    private String id;
    private String userId;
    private String artistId;
    private String eventName;
    private String eventType;
    private LocalDateTime eventDate;
    private String venue;
    private String description;
    private Double budget;
    private String status;

    @CreatedDate
    private LocalDateTime createdAt;
}
