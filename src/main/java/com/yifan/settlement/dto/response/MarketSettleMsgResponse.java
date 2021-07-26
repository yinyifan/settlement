package com.yifan.settlement.dto.response;

import com.yifan.settlement.dto.PayerParty;
import com.yifan.settlement.dto.ReceiverParty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MarketSettleMsgResponse {
    private String tradeId;
    private String messageId;
    private BigDecimal amount;
    private String valueDate;
    private String currency;
    private PayerParty payerParty;
    private ReceiverParty receiverParty;
    private String supportingInformation;
}
