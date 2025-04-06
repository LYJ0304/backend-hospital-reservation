package com.example.hospitalreservation.controller;

import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest request) {
        Reservation reservation = reservationService.createReservation(
                request.doctorId(), request.patientId(), request.reservationTime()
        );
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> cancelReservationJson(@PathVariable Long id,
                                                                     @RequestBody Map<String, String> payload) {
        String reason = payload.get("cancelReason");
        reservationService.cancelReservation(id, reason);
        logger.info("예약 ID: {}가 취소되었습니다. 사유: {}", id, reason);
        return ResponseEntity.ok(Map.of("message", "Reservation cancelled", "id", String.valueOf(id)));
    }
}

record ReservationRequest(Long doctorId, Long patientId, java.time.LocalDateTime reservationTime) {}
