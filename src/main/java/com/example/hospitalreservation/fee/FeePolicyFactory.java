package com.example.hospitalreservation.fee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FeePolicyFactory {
    private final Map<String, FeePolicy> feePolicyMap;

    @Autowired
    public FeePolicyFactory(Map<String, FeePolicy> feePolicyMap) {
        this.feePolicyMap = feePolicyMap;
    }

    public FeePolicy getPolicy(String reason) {
        return feePolicyMap.getOrDefault(reason, () -> 0);  // 기본 0원
    }
}
