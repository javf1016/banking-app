package com.example.bankingapp.application.port.in;

import com.example.bankingapp.adapters.outbound.TransferenciaJpaEntity;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.math.BigDecimal;

@Value
@EqualsAndHashCode(callSuper = false)
public class TransferirDineroCommand {

    private final String cuentaOrigen;

    private final String cuentaDestino;

    private final BigDecimal monto;

    public TransferirDineroCommand(String cuentaOrigen, String cuentaDestino, BigDecimal monto) {
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;

    }
}
