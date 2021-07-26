package com.yifan.settlement.service.impl;

import com.yifan.settlement.Exception.SettlementApiException;
import com.yifan.settlement.dto.request.SettlementCreationRequest;
import com.yifan.settlement.dto.request.SettlementRetrieveRequest;
import com.yifan.settlement.dto.response.MarketSettleMsgResponse;
import com.yifan.settlement.entity.SettlementMsg;
import com.yifan.settlement.repository.SettlementRepository;
import com.yifan.settlement.util.SettlementMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SettlementServiceImplTest {

    @Mock
    private SettlementRepository repository;

    @Mock
    private SettlementMapper mapper;

    @InjectMocks
    private final SettlementServiceImpl settlementService = new SettlementServiceImpl(repository, mapper);


    @Test
    public void testPersistNewSettlement_IdExistInDb_throwException(){
        when(mapper.mapSettlementCreationRequestToSettlementEntity(any())).thenReturn(new SettlementMsg());
        when(repository.existsById(anyString())).thenReturn(true);
        SettlementCreationRequest settlementCreationRequest = new SettlementCreationRequest();
        settlementCreationRequest.setTradeId("testTradeId");
        Assertions.assertThrows(SettlementApiException.class, ()->settlementService.persistNewSettlement(settlementCreationRequest));
    }

    @Test
    public void testPersistNewSettlement_validRequest_validResponse(){
        MarketSettleMsgResponse marketSettleMsgResponse = new MarketSettleMsgResponse();
        marketSettleMsgResponse.setTradeId("1234");
        when(mapper.mapSettlementCreationRequestToSettlementEntity(any())).thenReturn(new SettlementMsg());
        when(repository.existsById(anyString())).thenReturn(false);
        when(repository.save(any())).thenReturn(new SettlementMsg());
        when(mapper.mapEntityToSettleMsgResponse(any())).thenReturn(marketSettleMsgResponse);
        SettlementCreationRequest settlementCreationRequest = new SettlementCreationRequest();
        settlementCreationRequest.setTradeId("testTradeId");

        MarketSettleMsgResponse result = settlementService.persistNewSettlement(settlementCreationRequest);
        Assertions.assertEquals("1234",result.getTradeId());
    }

    @Test
    public void testFetchExistingSettlementByTradeId_tradeIdDoesNotExist_throwException(){
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        SettlementRetrieveRequest settlementRetrieveRequest = new SettlementRetrieveRequest();
        settlementRetrieveRequest.setTradeId("1234");
        Assertions.assertThrows(SettlementApiException.class, ()->settlementService.fetchExistingSettlementByTradeId(settlementRetrieveRequest));

    }

    @Test
    public void testFetchExistingSettlementByTradeId_validRequest_validResponse(){
        SettlementMsg settlementMsg = new SettlementMsg();
        settlementMsg.setTradeId("1234");
        MarketSettleMsgResponse marketSettleMsgResponse = new MarketSettleMsgResponse();
        marketSettleMsgResponse.setTradeId("1234");
        when(repository.findById(anyString())).thenReturn(Optional.of(settlementMsg));
        when(mapper.mapEntityToSettleMsgResponse(any())).thenReturn(marketSettleMsgResponse);
        SettlementRetrieveRequest settlementRetrieveRequest = new SettlementRetrieveRequest();
        settlementRetrieveRequest.setTradeId("1234");
        MarketSettleMsgResponse result = settlementService.fetchExistingSettlementByTradeId(settlementRetrieveRequest);
        Assertions.assertEquals("1234",result.getTradeId());

    }

}
