package com.yifan.settlement.service;

import com.yifan.settlement.dto.request.SettlementCreationRequest;
import com.yifan.settlement.dto.request.SettlementRetrieveRequest;
import com.yifan.settlement.dto.response.MarketSettleMsgResponse;

public interface SettlementService {

    /**
     *
     * @param settlementCreationRequest
     * @return the Settlement Msg Stored in DB
     */
    MarketSettleMsgResponse persistNewSettlement(SettlementCreationRequest settlementCreationRequest);

    /**
     *
     * @param settlementRetrieveRequest
     * @return existing Settlement Msg fetched by tradeId
     */
    MarketSettleMsgResponse fetchExistingSettlementByTradeId(SettlementRetrieveRequest settlementRetrieveRequest);
}
