package com.example.hospitalreservation.fee;

import org.springframework.stereotype.Component;

@Component("일반 검진")
public class GeneralCheckupFeePolicy implements FeePolicy{
    @Override
    public int calculateFee() {
        return 10000;
    }
}
