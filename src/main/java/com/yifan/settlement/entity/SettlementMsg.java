package com.yifan.settlement.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "settlement")
public class SettlementMsg {

    private String tradeId;
    private String messageId;
    private String ssiCode;
    private BigDecimal amount;
    private String currency;
    private String date;
    private LocalDate createdDate;
}
