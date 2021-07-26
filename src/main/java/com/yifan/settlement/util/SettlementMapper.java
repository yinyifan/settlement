package com.yifan.settlement.util;

import com.yifan.settlement.dto.request.SettlementCreationRequest;
import com.yifan.settlement.dto.response.MarketSettleMsgResponse;
import com.yifan.settlement.dto.PayerParty;
import com.yifan.settlement.dto.ReceiverParty;
import com.yifan.settlement.entity.SettlementMsg;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SettlementMapper {

    private final DateUtil dateUtil;
    private final SettlementMsgUtil msgUtil;

    public SettlementMapper(DateUtil dateUtil, SettlementMsgUtil msgUtil) {
        this.dateUtil = dateUtil;
        this.msgUtil = msgUtil;
    }

    public SettlementMsg mapSettlementCreationRequestToSettlementEntity(SettlementCreationRequest request){
        SettlementMsg settlementMsg = new SettlementMsg();
        settlementMsg.setMessageId(UUID.randomUUID().toString());
        settlementMsg.setAmount(request.getAmount());
        settlementMsg.setCreatedDate(LocalDateTime.now());
        settlementMsg.setCurrency(request.getCurrency());
        settlementMsg.setAmount(request.getAmount());
        settlementMsg.setTradeId(request.getTradeId());
        settlementMsg.setValueDate(dateUtil.convertDateStringToSettlementDateFormat(request.getValueDate()));
        settlementMsg.setPayerAccNo(msgUtil.getPayerAccNo(request.getSsiCode()));
        settlementMsg.setPayerBankCode(msgUtil.getPayerBankCode(request.getSsiCode()));
        settlementMsg.setReceiverAccNo(msgUtil.getReceiverAccNo(request.getSsiCode()));
        settlementMsg.setReceiverBankCode(msgUtil.getReceiverBankCode(request.getSsiCode()));
        settlementMsg.setSupportingInfo(msgUtil.getSupportInfo(request.getSsiCode()));

        return settlementMsg;
    }

    public MarketSettleMsgResponse mapEntityToSettleMsgResponse(SettlementMsg savedSettlementMsg) {
        MarketSettleMsgResponse response = new MarketSettleMsgResponse();
        response.setAmount(savedSettlementMsg.getAmount());
        response.setCurrency(savedSettlementMsg.getCurrency());
        response.setTradeId(savedSettlementMsg.getTradeId());
        response.setValueDate(savedSettlementMsg.getValueDate().toString());
        response.setMessageId(savedSettlementMsg.getMessageId().toString());
        PayerParty payerParty = new PayerParty();
        payerParty.setAccountNumber(savedSettlementMsg.getPayerAccNo());
        payerParty.setBankCode(savedSettlementMsg.getPayerBankCode());
        response.setPayerParty(payerParty);
        ReceiverParty receiverParty = new ReceiverParty();
        receiverParty.setBankCode(savedSettlementMsg.getReceiverBankCode());
        receiverParty.setAccountNumber(savedSettlementMsg.getReceiverAccNo());
        response.setReceiverParty(receiverParty);
        response.setSupportingInformation(savedSettlementMsg.getSupportingInfo());
        return response;
    }
}
