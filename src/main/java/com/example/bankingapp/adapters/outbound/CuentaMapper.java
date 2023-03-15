package com.example.bankingapp.adapters.outbound;

import com.example.bankingapp.domain.model.Cuenta;
import com.example.bankingapp.domain.model.Transferencia;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CuentaMapper {

    public Cuenta mapToDomainEntity(CuentaJpaEntity cuenta) {

        return Cuenta.cuentaId(cuenta.getId(),
                cuenta.getNumero(),
                cuenta.getSaldo());

    }

    public TransferenciaJpaEntity mapToJpaEntityTransferencia(Transferencia transferencia) {
        return new TransferenciaJpaEntity(
                transferencia.getId() == null ? null : transferencia.getId().getValue(),
                transferencia.getCuentaOrigen(),
                transferencia.getCuentaDestino(),
                transferencia.getMonto());

    }

    public CuentaJpaEntity mapToJpaEntityCuenta(Cuenta cuenta) {
        return new CuentaJpaEntity(
                cuenta.getId(),
                cuenta.getNumero(),
                cuenta.getSaldo());
    }

}