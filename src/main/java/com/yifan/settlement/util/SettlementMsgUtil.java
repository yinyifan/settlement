package com.yifan.settlement.util;

import com.yifan.settlement.dto.PayerParty;
import com.yifan.settlement.dto.ReceiverParty;
import com.yifan.settlement.dto.SettlementSSI;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SettlementMsgUtil {
    private static final Map<String, SettlementSSI > settlementSSIMap = new HashMap<>();
    static {
        //DBS_OCBC_1
        SettlementSSI dbsOcbc1 = new SettlementSSI();

        PayerParty dbsOcbc1Payer = new PayerParty();
        dbsOcbc1Payer.setAccountNumber("05461368");
        dbsOcbc1Payer.setBankCode("DBSSGB2LXXX");

        ReceiverParty dbsOcbc1Receiver = new ReceiverParty();
        dbsOcbc1Receiver.setAccountNumber("438421");
        dbsOcbc1Receiver.setBankCode("OCBCSGSGXXX");

        dbsOcbc1.setPayerParty(dbsOcbc1Payer);
        dbsOcbc1.setReceiverParty(dbsOcbc1Receiver);
        dbsOcbc1.setSupportInfo("BNF:PAY CLIENT");
        settlementSSIMap.put("DBS_OCBC_1",dbsOcbc1);

        //OCBC_DBS_1
        SettlementSSI ocbcDbs1 = new SettlementSSI();

        PayerParty ocbcDbs1Payer = new PayerParty();
        ocbcDbs1Payer.setAccountNumber("438421");
        ocbcDbs1Payer.setBankCode("OCBCSGSGXXX");

        ReceiverParty ocbcDbs1Receiver = new ReceiverParty();
        ocbcDbs1Receiver.setAccountNumber("05461368");
        ocbcDbs1Receiver.setBankCode("DBSSGB2LXXX");

        ocbcDbs1.setPayerParty(dbsOcbc1Payer);
        ocbcDbs1.setReceiverParty(ocbcDbs1Receiver);
        ocbcDbs1.setSupportInfo("BNF:FFC-4697132");
        settlementSSIMap.put("OCBC_DBS_1",ocbcDbs1);

        //OCBC_DBS_2
        SettlementSSI ocbcDbs2 = new SettlementSSI();

        PayerParty ocbcDbs2Payer = new PayerParty();
        ocbcDbs2Payer.setAccountNumber("438421");
        ocbcDbs2Payer.setBankCode("OCBCSGSGXXX");

        ReceiverParty ocbcDbs2Receiver = new ReceiverParty();
        ocbcDbs2Receiver.setAccountNumber("05461369");
        ocbcDbs2Receiver.setBankCode("DBSSSGSGXXX");

        ocbcDbs2.setPayerParty(dbsOcbc1Payer);
        ocbcDbs2.setReceiverParty(ocbcDbs2Receiver);
        ocbcDbs2.setSupportInfo("BNF:FFC-482315");
        settlementSSIMap.put("OCBC_DBS_2",ocbcDbs2);

        //DBS_SCB
        SettlementSSI dbsScb = new SettlementSSI();

        PayerParty dbsScbPayer = new PayerParty();
        dbsScbPayer.setAccountNumber("185586");
        dbsScbPayer.setBankCode("DBSSSGSGXXX");

        ReceiverParty dbsScbReceiver = new ReceiverParty();
        dbsScbReceiver.setAccountNumber("1868422");
        dbsScbReceiver.setBankCode("SCBLAU2SXXX");

        dbsScb.setPayerParty(dbsOcbc1Payer);
        dbsScb.setReceiverParty(dbsScbReceiver);
        dbsScb.setSupportInfo("RFB:Test payment");
        settlementSSIMap.put("DBS_SCB",dbsScb);

        //CITI_GS
        SettlementSSI citiGs = new SettlementSSI();

        PayerParty citiGsPayer = new PayerParty();
        citiGsPayer.setAccountNumber("00454983");
        citiGsPayer.setBankCode("CITIGB2LXXX");

        ReceiverParty citiGsReceiver = new ReceiverParty();
        citiGsReceiver.setAccountNumber("48486414");
        citiGsReceiver.setBankCode("GSCMUS33XXX");

        citiGs.setPayerParty(dbsOcbc1Payer);
        citiGs.setReceiverParty(citiGsReceiver);
        citiGs.setSupportInfo("");
        settlementSSIMap.put("CITI_GS",citiGs);

    }


    public String getPayerAccNo(String ssiCode){
        return settlementSSIMap.get(ssiCode).getPayerParty().getAccountNumber();
    }

    public String getPayerBankCode(String ssiCode){
        return settlementSSIMap.get(ssiCode).getPayerParty().getBankCode();
    }

    public String getReceiverAccNo(String ssiCode){
        return  settlementSSIMap.get(ssiCode).getReceiverParty().getAccountNumber();
    }

    public String getReceiverBankCode(String ssiCode){
        return settlementSSIMap.get(ssiCode).getReceiverParty().getBankCode();
    }

    public String getSupportInfo(String ssiCode){
        return settlementSSIMap.get(ssiCode).getSupportInfo();
    }
}
