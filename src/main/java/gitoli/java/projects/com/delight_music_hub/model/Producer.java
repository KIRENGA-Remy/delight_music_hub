package gitoli.java.projects.com.delight_music_hub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "producers")
public class Producer {
    @Id
    private String id;
    private String userId;
    private String stageName;
    private String bio;
    private String specialty;
    private String profileImage;
    private Double hourlyRate;
    private Integer yearsExperience;
    private Set<String> genres = new HashSet<>();
    private boolean isAvailable = true;

    @CreatedDate
    private LocalDateTime createdAt;
}
