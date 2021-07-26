package com.yifan.settlement.util;

import com.yifan.settlement.Exception.SettlementApiException;
import com.yifan.settlement.dto.request.SettlementCreationRequest;
import com.yifan.settlement.dto.response.MarketSettleMsgResponse;
import com.yifan.settlement.entity.SettlementMsg;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static com.yifan.settlement.Exception.ErrorCodeAndMsg.SETTLEMENT_MSG_SSI_CODE_DOES_NOT_EXIST;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SettlementMapperTest {

    @Mock
    private DateUtil dateUtil;

    @Mock
    private SettlementMsgUtil msgUtil;

    @InjectMocks
    private final SettlementMapper settlementMapper = new SettlementMapper(dateUtil,msgUtil);

    private SettlementCreationRequest settlementCreationRequest;
    private SettlementMsg settlementMsg;

    private static final BigDecimal AMOUNT = new BigDecimal("12345.67");
    private static final String CURRENCY = "testCurrency";
    private static final String SSI_CODE = "testSsiCode";
    private static final String TRADE_ID = "testTradeId";
    private static final String VALUE_DATE = "testValueDate";
    private static final String PAYER_ACC = "testPayerAcc";
    private static final String PAYER_BANK_CODE = "testPayerBankCode";
    private static final String RECEIVER_ACC_NO = "testReceiverAccNo";
    private static final String RECEIVER_BANK_CODE = "testReceiverBankCode";
    private static final String SUPPORT_INFO = "testSupportInfo";
    private static final String MSG_ID = "testMsgId";


    @BeforeAll
    public void init(){
        settlementCreationRequest = new SettlementCreationRequest();
        settlementCreationRequest.setAmount(AMOUNT);
        settlementCreationRequest.setCurrency(CURRENCY);
        settlementCreationRequest.setSsiCode(SSI_CODE);
        settlementCreationRequest.setTradeId(TRADE_ID);
        settlementCreationRequest.setValueDate(VALUE_DATE);

        settlementMsg =new SettlementMsg();
        settlementMsg.setSupportingInfo(SUPPORT_INFO);
        settlementMsg.setReceiverAccNo(RECEIVER_ACC_NO);
        settlementMsg.setReceiverBankCode(RECEIVER_BANK_CODE);
        settlementMsg.setPayerBankCode(PAYER_BANK_CODE);
        settlementMsg.setPayerAccNo(PAYER_ACC);
        settlementMsg.setCreatedDate(LocalDateTime.now());
        settlementMsg.setAmount(AMOUNT);
        settlementMsg.setCurrency(CURRENCY);
        settlementMsg.setTradeId(TRADE_ID);
        settlementMsg.setValueDate(LocalDate.now());
        settlementMsg.setMessageId(MSG_ID);

    }

    @Test
    public void testMapSettlementCreationRequestToSettlementEntity_invalidSsiCode_throwException(){
        when(dateUtil.convertDateStringToSettlementDateFormat(anyString())).thenReturn(LocalDate.now());
        when(msgUtil.getPayerAccNo(anyString())).thenThrow(new SettlementApiException(HttpStatus.BAD_REQUEST,SETTLEMENT_MSG_SSI_CODE_DOES_NOT_EXIST));

        Assertions.assertThrows(SettlementApiException.class, ()->settlementMapper.mapSettlementCreationRequestToSettlementEntity(settlementCreationRequest));
    }

    @Test
    public void testMapSettlementCreationRequestToSettlementEntity_invalidTradeDate_throwException(){
        when(dateUtil.convertDateStringToSettlementDateFormat(anyString())).thenThrow(new DateTimeParseException("test","test",1));

        Assertions.assertThrows(DateTimeParseException.class, ()->settlementMapper.mapSettlementCreationRequestToSettlementEntity(settlementCreationRequest));
    }

    @Test
    public void testMapSettlementCreationRequestToSettlementEntity_validRequest_returnValidResponse(){
        when(dateUtil.convertDateStringToSettlementDateFormat(anyString())).thenReturn(LocalDate.now());
        when(msgUtil.getPayerAccNo(anyString())).thenReturn(PAYER_ACC);
        when(msgUtil.getPayerBankCode(anyString())).thenReturn(PAYER_BANK_CODE);
        when(msgUtil.getReceiverAccNo(anyString())).thenReturn(RECEIVER_ACC_NO);
        when(msgUtil.getReceiverBankCode(anyString())).thenReturn(RECEIVER_BANK_CODE);
        when(msgUtil.getSupportInfo(anyString())).thenReturn(SUPPORT_INFO);
        SettlementMsg result = settlementMapper.mapSettlementCreationRequestToSettlementEntity(settlementCreationRequest);
        Assertions.assertAll("valid localDate returned",
                ()-> Assertions.assertEquals(result.getAmount(), AMOUNT),
                ()-> Assertions.assertEquals(result.getCurrency(), CURRENCY),
                ()-> Assertions.assertEquals(result.getTradeId(), TRADE_ID),
                ()-> Assertions.assertEquals(result.getValueDate(), LocalDate.now()),
                ()-> Assertions.assertEquals(result.getPayerBankCode(), PAYER_BANK_CODE),
                ()-> Assertions.assertEquals(result.getReceiverAccNo(), RECEIVER_ACC_NO),
                ()-> Assertions.assertEquals(result.getReceiverBankCode(), RECEIVER_BANK_CODE),
                ()-> Assertions.assertEquals(result.getSupportingInfo(), SUPPORT_INFO),
                ()-> Assertions.assertEquals(result.getPayerAccNo(), PAYER_ACC));
    }

    @Test
    public void testMapEntityToSettleMsgResponse_validRequestObject_returnValidResponse(){
        MarketSettleMsgResponse result = settlementMapper.mapEntityToSettleMsgResponse(settlementMsg);

        Assertions.assertAll("valid localDate returned",
                ()-> Assertions.assertEquals(result.getAmount(), AMOUNT),
                ()-> Assertions.assertEquals(result.getCurrency(), CURRENCY),
                ()-> Assertions.assertEquals(result.getTradeId(), TRADE_ID),
                ()-> Assertions.assertEquals(result.getPayerParty().getBankCode(), PAYER_BANK_CODE),
                ()-> Assertions.assertEquals(result.getReceiverParty().getAccountNumber(), RECEIVER_ACC_NO),
                ()-> Assertions.assertEquals(result.getReceiverParty().getBankCode(), RECEIVER_BANK_CODE),
                ()-> Assertions.assertEquals(result.getSupportingInformation(), SUPPORT_INFO),
                ()-> Assertions.assertEquals(result.getPayerParty().getAccountNumber(), PAYER_ACC));

    }
}
