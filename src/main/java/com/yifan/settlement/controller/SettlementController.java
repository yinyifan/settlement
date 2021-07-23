package com.yifan.settlement.controller;

import com.yifan.settlement.controller.request.SettlementCreationRequest;
import com.yifan.settlement.controller.request.SettlementRetrieveRequest;
import com.yifan.settlement.controller.response.MarketSettleMsgResponse;
import com.yifan.settlement.service.SettlementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("/v1/settlement")
public class SettlementController {

    private final SettlementService settlementService;

    public SettlementController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @PostMapping
    public MarketSettleMsgResponse createSettlement(@Valid SettlementCreationRequest settlementCreationRequest){
        return settlementService.persistNewSettlement(settlementCreationRequest);
    }

    @GetMapping
    public MarketSettleMsgResponse retrieveSettlement(@Valid SettlementRetrieveRequest settlementRetrieveRequest){
        return settlementService.fetchExistingSettlementByTradeId(settlementRetrieveRequest);
    }
}
