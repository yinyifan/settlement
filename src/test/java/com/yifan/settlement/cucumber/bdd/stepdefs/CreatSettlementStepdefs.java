package com.yifan.settlement.cucumber.bdd.stepdefs;

import com.yifan.settlement.dto.request.SettlementCreationRequest;

import com.yifan.settlement.repository.SettlementRepository;
import cucumber.api.java.en.Given;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class CreatSettlementStepdefs extends AbstractSteps implements En {
    @Autowired
    private SettlementRepository repository;

    public CreatSettlementStepdefs() {
        Given("^user wants to create an Settlement Message with the following attributes \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$", (String arg0, String arg1, String arg2, String arg3, String arg4) -> {
            testContext().reset();
            super.testContext().setPayload(new SettlementCreationRequest(arg0,arg1,new BigDecimal(arg2),arg3,arg4));
        });
        When("^user hit the POST url \"([^\"]*)\"$", (String arg0) -> {
            String createSettlementUrl = "/v1/settlement";

            executePost(createSettlementUrl);
        });

        Then("the save {string}", (String expectedResult) -> {
            repository.deleteById("1234567");
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
