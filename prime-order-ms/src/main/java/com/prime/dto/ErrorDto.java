package com.prime.dto;

import java.util.Calendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private int code;
    private String technicalMessage;
    private String userMessage;
    private Calendar timestamp;
}
