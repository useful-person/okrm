package com.useful_person.ai.dto;

import lombok.Data;

@Data
public class RefundAuditResult {
    private boolean approve;
    private String reason;
}
