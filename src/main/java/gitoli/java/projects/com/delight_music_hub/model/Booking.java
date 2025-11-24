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
@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;
    private String userId;
    private String studioRoomId;
    private String producerId;
    private LocalDateTime bookingDate;
    private Integer durationHours;
    private String serviceType;
    private String projectDescription;
    private String status;
    private Double totalAmount;
    private String paymentId;

    @CreatedDate
    private LocalDateTime createdDate;
}
