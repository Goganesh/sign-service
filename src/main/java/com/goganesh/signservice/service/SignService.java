package com.goganesh.signservice.service;

import com.goganesh.signservice.dto.SignResponse;
import org.springframework.util.MultiValueMap;

public interface SignService {

    SignResponse signOperation(MultiValueMap<String, String> signRequest);
}
