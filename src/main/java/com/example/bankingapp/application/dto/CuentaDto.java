package com.example.bankingapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDto {
    private Long id;
    private String numero;
    private BigDecimal saldo;

}
