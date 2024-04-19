package com.goganesh.signservice.service;

import com.goganesh.signservice.dto.SignResponse;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static com.goganesh.signservice.dto.SignResponse.Status.SUCCESS;
import static org.junit.jupiter.api.Assertions.*;

class SignServiceHmacSHA256Test {

    @Test
    void signOperationSuccess() {
        String key = "12345";
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("atr1", "val1");
        request.add("atr3", "val3");
        request.add("atr2", "val2");

        SignService signService = new SignServiceHmacSHA256(key);
        SignResponse response = signService.signOperation(request);

        assertEquals(response.getStatus(), SUCCESS);
        assertEquals(((Map) response.getResult().get(0)).get("signature"), "875a44f855150d98fe0cc7620d4c5e0de3a357c5d693ccb575e004af8ad328f8");
    }

}
