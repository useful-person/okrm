package com.useful_person.ai.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RefundContext {
    private String orderNo;
    private BigDecimal amount;
    private String orderTime;
    private String refundTime;
    private String productType;
    private int refundCount;
    private String reason;
}
