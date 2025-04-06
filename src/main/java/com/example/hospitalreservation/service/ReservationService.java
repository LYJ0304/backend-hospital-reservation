package com.example.hospitalreservation.service;

import com.example.hospitalreservation.model.Reservation;
import com.example.hospitalreservation.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(Long doctorId, Long patientId, LocalDateTime reservationTime) {
        LocalDateTime startTime = reservationTime.withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endTime = startTime.plusMinutes(1);

        boolean isOverlapping = reservationRepository.existsByDoctorIdAndReservationTimeBetween(doctorId, startTime, endTime);
        if (isOverlapping) {
            throw new IllegalArgumentException("해당 시간에는 이미 예약이 있습니다. 다른 시간을 선택해주세요.");
        }

        Reservation reservation = new Reservation(doctorId, patientId, reservationTime);
        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long id, String cancellationReason) {
        Reservation reservation = reservationRepository.findById(id);
        if (reservation == null) {
            throw new IllegalArgumentException("예약을 찾을 수 없습니다.");
        }

        reservation.setStatus("CANCELED");
        reservation.setCancellationReason(cancellationReason);
        reservation.setCanceledAt(LocalDateTime.now());

        reservationRepository.update(reservation);
        logger.info("Reservation {} canceled. Reason: {}", id, cancellationReason);
    }
}