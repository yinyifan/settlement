package com.yifan.settlement.service;

import com.yifan.settlement.dto.request.SettlementCreationRequest;
import com.yifan.settlement.dto.request.SettlementRetrieveRequest;
import com.yifan.settlement.dto.response.MarketSettleMsgResponse;

public interface SettlementService {

    MarketSettleMsgResponse persistNewSettlement(SettlementCreationRequest settlementCreationRequest);

    MarketSettleMsgResponse fetchExistingSettlementByTradeId(SettlementRetrieveRequest settlementRetrieveRequest);
}
