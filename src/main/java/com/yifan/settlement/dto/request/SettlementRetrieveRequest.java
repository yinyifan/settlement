package com.yifan.settlement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettlementRetrieveRequest {
    @NotBlank(message = "Trade ID can not be null or empty!")
    private String tradeId;
}
