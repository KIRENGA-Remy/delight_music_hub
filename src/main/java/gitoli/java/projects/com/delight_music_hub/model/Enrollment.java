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
@Document(collection = "enrollments")
public class Enrollment {
    @Id
    private String id;
    private String userId;
    private String musicClassId;
    private LocalDateTime enrollmentDate;
    private String status;
    private String paymentId;
    private Integer sessionsCompleted = 0;
    private Integer totalSessions;

    @CreatedDate
    private LocalDateTime createdAt;
}
