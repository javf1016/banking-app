package com.example.bankingapp.domain.service.impl;

import com.example.bankingapp.adapters.outbound.*;
import com.example.bankingapp.application.port.in.TransferirDineroCommand;
import com.example.bankingapp.application.port.in.TransferirDineroUseCase;
import com.example.bankingapp.application.port.out.CargarCuentaPort;
import com.example.bankingapp.domain.exception.CuentaNotFoundException;
import com.example.bankingapp.application.exception.SaldoInsuficienteException;
import com.example.bankingapp.domain.model.Cuenta;
import com.example.bankingapp.domain.model.Transferencia;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransferenciaServiceImpl implements TransferirDineroUseCase {

    private final TransferenciaRepository transferenciaRepository;
    private final SpringCuentaRepository cuentaRepository;
    private final CargarCuentaPort cargarCuentaPort;

    private final CuentaMapper cuentaMapper;

    @Override
    @Transactional
    public TransferenciaJpaEntity transferirDinero(TransferirDineroCommand command) {
        Optional<CuentaJpaEntity> optionalCuentaOrigen = cuentaRepository.findByNumero(command.getCuentaOrigen());
        Optional<CuentaJpaEntity> optionalCuentaDestino = cuentaRepository.findByNumero(command.getCuentaDestino());
        if (optionalCuentaOrigen.isPresent() && optionalCuentaDestino.isPresent()) {
            Cuenta cuentaOrigen = cuentaMapper.mapToDomainEntity(optionalCuentaOrigen.get());
            Cuenta cuentaDestino = cuentaMapper.mapToDomainEntity(optionalCuentaDestino.get());
            if (cuentaOrigen.getSaldo().compareTo(command.getMonto()) >= 0) {
                BigDecimal nuevoSaldoOrigen = cuentaOrigen.getSaldo().subtract(command.getMonto());
                BigDecimal nuevoSaldoDestino = cuentaDestino.getSaldo().add(command.getMonto());
                cargarCuentaPort.actualizarSaldo(cuentaOrigen.getNumero(), nuevoSaldoOrigen);
                cargarCuentaPort.actualizarSaldo(cuentaDestino.getNumero(), nuevoSaldoDestino);
                TransferenciaJpaEntity transferenciaJpaEntity = new TransferenciaJpaEntity(cuentaOrigen.getNumero(), cuentaDestino.getNumero(), command.getMonto());
                transferenciaRepository.save(transferenciaJpaEntity);
                return transferenciaJpaEntity;
            } else {
                throw new SaldoInsuficienteException("Saldo insuficiente en la cuenta origen");
            }
        } else {
            throw new CuentaNotFoundException("Cuenta origen o destino no encontrada");
        }
    }

}