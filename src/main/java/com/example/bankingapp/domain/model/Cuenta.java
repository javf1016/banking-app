package com.example.bankingapp.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cuenta {

    @Getter
    private Long id;

    @Getter
    private String numero;

    @Getter
    private BigDecimal saldo;

    public static Cuenta cuentaId(
            Long id,
            String numero,
            BigDecimal saldo) {
        return new Cuenta(id, numero, saldo);
    }

    public Cuenta(String numero, BigDecimal saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public static Cuenta descuentoCuenta(Cuenta cuentaOrigen, Cuenta cuentaDestino ) {
        return new Cuenta(cuentaOrigen.getId(),cuentaOrigen.getNumero(),cuentaOrigen.getSaldo().subtract(cuentaDestino.getSaldo()));
    }


}
