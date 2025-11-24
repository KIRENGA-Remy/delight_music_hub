package gitoli.java.projects.com.delight_music_hub.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "payments")
public class Payment {
    @Id
    private String id;
    private String userId;
    private String transactionId;
    private String paymentMethod;
    private String phoneNumber;
    private Double amount;
    private String currency = "RWF";
    private String status;
    private String referenceType;
    private String referenceId;
    private String callbackUrl;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
