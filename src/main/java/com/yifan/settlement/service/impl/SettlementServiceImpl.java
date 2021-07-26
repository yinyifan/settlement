package com.yifan.settlement.service.impl;

import com.yifan.settlement.Exception.ErrorCodeAndMsg;
import com.yifan.settlement.Exception.SettlementApiException;
import com.yifan.settlement.dto.request.SettlementCreationRequest;
import com.yifan.settlement.dto.request.SettlementRetrieveRequest;
import com.yifan.settlement.dto.response.MarketSettleMsgResponse;
import com.yifan.settlement.entity.SettlementMsg;
import com.yifan.settlement.repository.SettlementRepository;
import com.yifan.settlement.service.SettlementService;
import com.yifan.settlement.util.SettlementMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettlementServiceImpl implements SettlementService {

    private final SettlementRepository repository;
    private final SettlementMapper mapper;

    public SettlementServiceImpl(SettlementRepository repository, SettlementMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MarketSettleMsgResponse persistNewSettlement(SettlementCreationRequest settlementCreationRequest) {
        SettlementMsg settlementMsg = mapper.mapSettlementCreationRequestToSettlementEntity(settlementCreationRequest);
        if(repository.existsById(settlementCreationRequest.getTradeId())){
            throw new SettlementApiException(HttpStatus.BAD_REQUEST, ErrorCodeAndMsg.SETTLEMENT_MSG_CREATE_ALREADY_EXIST);
        }
        SettlementMsg savedSettlementMsg = repository.save(settlementMsg);
        return mapper.mapEntityToSettleMsgResponse(savedSettlementMsg);
    }

    @Override
    public MarketSettleMsgResponse fetchExistingSettlementByTradeId(SettlementRetrieveRequest settlementRetrieveRequest) {
        Optional<SettlementMsg> response = repository.findById(settlementRetrieveRequest.getTradeId());
        if(!response.isPresent()){
           throw new SettlementApiException(HttpStatus.NOT_FOUND, ErrorCodeAndMsg.SETTLEMENT_MSG_GET_DOES_NOT_EXIST);
        }
        else{
            return mapper.mapEntityToSettleMsgResponse(response.get());
        }
    }
}
