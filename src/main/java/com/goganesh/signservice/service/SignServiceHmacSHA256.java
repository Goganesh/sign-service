package com.goganesh.signservice.service;

import com.goganesh.signservice.dto.SignResponse;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SignServiceHmacSHA256 implements SignService {
    private static final String ALGORITHM = "HmacSHA256";

    private final String key;

    public SignServiceHmacSHA256(@Value("${com.goganesh.signservice.algorithm.hmacsha256.key}") String key) {
        this.key = key;
    }

    @Override
    public SignResponse signOperation(MultiValueMap<String, String> signRequest) {
        String data = signRequest.toSingleValueMap()
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        String signature = new HmacUtils(ALGORITHM, key).hmacHex(data);

        return new SignResponse(SignResponse.Status.SUCCESS, Collections.singletonList(Collections.singletonMap("signature", signature)));
    }

}
