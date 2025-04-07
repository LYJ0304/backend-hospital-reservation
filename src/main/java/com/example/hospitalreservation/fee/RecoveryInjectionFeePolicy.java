package com.example.hospitalreservation.fee;

import org.springframework.stereotype.Component;

@Component("피로 회복 주사")
public class RecoveryInjectionFeePolicy implements FeePolicy {
    @Override
    public int calculateFee() {
        return 25000;
    }
}
