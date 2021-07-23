package com.yifan.settlement.service;

import com.yifan.settlement.controller.request.SettlementCreationRequest;
import com.yifan.settlement.controller.request.SettlementRetrieveRequest;
import com.yifan.settlement.controller.response.MarketSettleMsgResponse;

public interface SettlementService {

    MarketSettleMsgResponse persistNewSettlement(SettlementCreationRequest settlementCreationRequest);

    MarketSettleMsgResponse fetchExistingSettlementByTradeId(SettlementRetrieveRequest settlementRetrieveRequest);
}
