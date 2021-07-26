package com.yifan.settlement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettlementCreationRequest {
    @NotBlank(message = "Trade ID can not be null or empty!")
    private String tradeId;
    @NotBlank(message = "SSI Code can not be null or empty!")
    private String ssiCode;
    @NotNull(message = "amount can not be null or empty!")
    private BigDecimal amount;
    @NotBlank(message = "currency can not be null or empty!")
    private String currency;
    @NotBlank(message = "date can not be null or empty!")
    private String valueDate;

}
