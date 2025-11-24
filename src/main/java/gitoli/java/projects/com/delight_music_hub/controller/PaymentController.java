package gitoli.java.projects.com.delight_music_hub.controller;

import gitoli.java.projects.com.delight_music_hub.dto.PaymentRequest;
import gitoli.java.projects.com.delight_music_hub.model.Payment;
import gitoli.java.projects.com.delight_music_hub.repository.PaymentRepository;
import gitoli.java.projects.com.delight_music_hub.security.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Tag(name = "Payments", description = "Payment processing")
@SecurityRequirement(name = "Bearer Authentication")
public class PaymentController {
    private final PaymentRepository paymentRepository;

    @PostMapping("/initiate")
    @Operation(summary = "Initiate payment")
    public ResponseEntity<?> initiatePayment(@Valid @RequestBody PaymentRequest request,
                                             Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Payment payment = new Payment();
        payment.setUserId(userDetails.getId());
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setPhoneNumber(request.getPhoneNumber());
        payment.setAmount(request.getAmount());
        payment.setReferenceType(request.getReferenceType());
        payment.setReferenceId(request.getReferenceId());
        payment.setStatus("PENDING");

        Payment saved = paymentRepository.save(payment);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    @Operation(summary = "Get user payments")
    public ResponseEntity<List<Payment>> getUserPayments(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(paymentRepository.findByUserId(userDetails.getId()));
    }

    @PostMapping("/callback/{transactionId}")
    @Operation(summary = "Payment callback (Mock)")
    public ResponseEntity<?> paymentCallback(@PathVariable String transactionId,
                                             @RequestParam String status) {
        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(status);
        paymentRepository.save(payment);

        return ResponseEntity.ok("Payment updated");
    }
}