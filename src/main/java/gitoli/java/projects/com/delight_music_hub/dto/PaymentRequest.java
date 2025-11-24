package gitoli.java.projects.com.delight_music_hub.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PaymentRequest {
    @NotBlank
    private String paymentMethod;

    @NotBlank
    @Pattern(regexp = "^\\+?[0-9]{10,13}$")
    private String phoneNumber;

    @NotNull
    @Min(100)
    private Double amount;

    @NotBlank
    private String referenceType;

    @NotBlank
    private String referenceId;
}
