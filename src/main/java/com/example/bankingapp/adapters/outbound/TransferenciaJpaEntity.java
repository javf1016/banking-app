package com.example.bankingapp.adapters.outbound;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "transferencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cuenta_origen", nullable = false)
    private String cuentaOrigen;

    @Column(name = "cuenta_destino", nullable = false)
    private String cuentaDestino;

    @Column(nullable = false)
    private BigDecimal monto;

    public TransferenciaJpaEntity(TransferenciaJpaEntity mapToJpaEntityTransferencia) {
    }

    public TransferenciaJpaEntity(String cuentaOrigen, String cuentaDestino, BigDecimal monto) {
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
    }
}
