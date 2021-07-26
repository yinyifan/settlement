package com.yifan.settlement.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SettlementErrorResponse {
    private String errorCode;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;


    @Override
    public String toString() {
        return
                "errorCode='" + errorCode + '\'' +
                ", httpStatus=" + httpStatus +
                ", timestamp=" + timestamp;
    }
}
