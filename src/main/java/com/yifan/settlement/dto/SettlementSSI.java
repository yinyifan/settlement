package com.yifan.settlement.dto;

import lombok.Data;

@Data
public class SettlementSSI {
    private PayerParty payerParty;
    private ReceiverParty receiverParty;
    private String supportInfo;
}
