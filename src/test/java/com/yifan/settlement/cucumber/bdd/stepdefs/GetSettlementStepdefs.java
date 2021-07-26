package com.yifan.settlement.cucumber.bdd.stepdefs;

import com.yifan.settlement.dto.request.SettlementCreationRequest;
import com.yifan.settlement.dto.request.SettlementRetrieveRequest;
import com.yifan.settlement.entity.SettlementMsg;
import com.yifan.settlement.repository.SettlementRepository;
import com.yifan.settlement.service.SettlementService;
import cucumber.api.java8.En;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class GetSettlementStepdefs extends AbstractSteps implements En {
    @Autowired
    private SettlementRepository repository;
    @Autowired
    private SettlementService settlementService;

    public GetSettlementStepdefs() {
        When("^user request for existing settlement msg with existing trade id$", () -> {
            int tradeIdToInsert = repository.getMaxTradeId()+1;
            SettlementCreationRequest settlementCreationRequest = new SettlementCreationRequest();
            settlementCreationRequest.setAmount(new BigDecimal("123456.78"));
            settlementCreationRequest.setCurrency("USD");
            settlementCreationRequest.setSsiCode("CITI_GS");
            settlementCreationRequest.setTradeId(Integer.toString(tradeIdToInsert));
            settlementCreationRequest.setValueDate("01012021");
            settlementService.persistNewSettlement(settlementCreationRequest);

            String fetchSettlementUrl = "/v1/settlement?tradeId="+ tradeIdToInsert;
            executeGet(fetchSettlementUrl);
            
        });
        Then("the fetch {string}", (String expectedResult) -> {
            Response response = testContext().getResponse();

            switch (expectedResult) {
                case "IS SUCCESSFUL":
                    assertThat(response.statusCode()).isIn(200);
                    break;
                case "FAILS":
                    assertThat(response.statusCode()).isBetween(400, 504);
                    break;
                default:
                    fail("Unexpected error");
            }
        });
    }
}
