package com.example.rechargesbatch.client;

import com.example.rechargesbatch.config.RechargesApiProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestTemplateRechargesApiClient implements RechargesApiClient {

    private final RestTemplate restTemplate;
    private final RechargesApiProperties props;

    @Override
    public void patchRechargesStatus() {
        String url = props.endpoint();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> voidHttpEntity = new HttpEntity<>(null,headers);
        ResponseEntity<ResponsePaymentStatus> response =
                restTemplate.exchange(url, HttpMethod.PATCH, voidHttpEntity, ResponsePaymentStatus.class);
        log.info("PATCH {} -> status={}", url, response.getStatusCode().value());
    }
}
