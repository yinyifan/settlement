package com.yifan.settlement.controller.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SettlementRetrieveRequest {
    @NotBlank(message = "Trade ID can not be null or empty!")
    private String tradeId;
}
