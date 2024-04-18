package com.goganesh.signservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignResponse {


    private Status status;
    private List<Object> result = new ArrayList<>();

    public enum Status {
        SUCCESS
    }

}
