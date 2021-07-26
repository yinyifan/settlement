package com.yifan.settlement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
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
