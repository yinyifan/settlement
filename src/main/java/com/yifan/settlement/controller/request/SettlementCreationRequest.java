package com.yifan.settlement.controller.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class SettlementCreationRequest {
    @NotBlank(message = "Trade ID can not be null or empty!")
    private String tradeId;
    @NotBlank(message = "SSI Code can not be null or empty!")
    private String ssiCode;
    @NotBlank(message = "amount can not be null or empty!")
    private BigDecimal amount;
    @NotBlank(message = "currency can not be null or empty!")
    private String currency;
    @NotBlank(message = "date can not be null or empty!")
    private String date;
}
