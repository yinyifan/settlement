package com.yifan.settlement.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class SettlementApiException extends RuntimeException{
    private HttpStatus httpStatus;
    private ErrorCodeAndMsg errorCodeAndMsg;
}
