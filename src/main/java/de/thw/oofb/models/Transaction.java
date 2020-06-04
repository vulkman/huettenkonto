package de.thw.oofb.models;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {
    LocalDateTime timestamp;
    Purpose purpose;
    Double amount;
}