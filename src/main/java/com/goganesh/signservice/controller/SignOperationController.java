package com.goganesh.signservice.controller;

import com.goganesh.signservice.dto.SignResponse;
import com.goganesh.signservice.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sign/operations/")
public class SignOperationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SignService signService;

    public SignOperationController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping(value = "/{operationId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PreAuthorize("@environment.getProperty('com.goganesh.signservice.auth.key') == #header")
    public SignResponse signOperation(@PathVariable String operationId,
                                      @RequestHeader(value = "Token", required = false) String header,
                                      @RequestBody MultiValueMap<String, String> signRequest) {
        logger.info("Handle sign operation by id " + operationId);
        return signService.signOperation(signRequest);
    }
}
