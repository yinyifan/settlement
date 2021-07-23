package com.yifan.settlement.service.impl;

import com.yifan.settlement.controller.request.SettlementCreationRequest;
import com.yifan.settlement.controller.request.SettlementRetrieveRequest;
import com.yifan.settlement.controller.response.MarketSettleMsgResponse;
import com.yifan.settlement.service.SettlementService;

public class SettlementServiceImpl implements SettlementService {
    @Override
    public MarketSettleMsgResponse persistNewSettlement(SettlementCreationRequest settlementCreationRequest) {
        return null;
    }

    @Override
    public MarketSettleMsgResponse fetchExistingSettlementByTradeId(SettlementRetrieveRequest settlementRetrieveRequest) {
        return null;
    }
}
