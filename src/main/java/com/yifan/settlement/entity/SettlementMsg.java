package com.yifan.settlement.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity
@Table(name = "client_settlement")
public class SettlementMsg {
    @Id
    @Column(name = "trade_id")
    private String tradeId;

    @Column(name = "message_id")
    private String messageId;

    private BigDecimal amount;

    @Column(name = "value_date")
    private LocalDate valueDate;

    private String currency;

    @Column(name = "payer_account_number")
    private String payerAccNo;

    @Column(name = "payer_bank_code")
    private String payerBankCode;

    @Column(name = "receiver_account_number")
    private String receiverAccNo;

    @Column(name = "receiver_bank_code")
    private String receiverBankCode;

    @Column(name = "supporting_information")
    private String supportingInfo;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
