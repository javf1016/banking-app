package com.example.bankingapp.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Value
@RequiredArgsConstructor
public class Transferencia {

    @Getter
    private TransferenciaId id;

    @Getter
    private String cuentaOrigen;

    @Getter
    private String cuentaDestino;

    @Getter
    private BigDecimal monto;

    @Value
    public static class TransferenciaId {
        private final Long value;
    }

    @JsonCreator
    public Transferencia(@JsonProperty("cuentaOrigen") String cuentaOrigen,
                         @JsonProperty("cuentaDestino") String cuentaDestino,
                         @JsonProperty("monto") BigDecimal monto) {
        this.id=null;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
    }

}
