package com.example.rechargesbatch.service;

import com.example.rechargesbatch.client.RechargesApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RechargesStatusService {

    private final RechargesApiClient apiClient;

    public void updateStatusViaApi() {
        apiClient.patchRechargesStatus();
    }
}
