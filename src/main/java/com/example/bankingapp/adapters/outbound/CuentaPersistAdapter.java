package com.example.bankingapp.adapters.outbound;

import com.example.bankingapp.application.port.out.CargarCuentaPort;
import com.example.bankingapp.common.PersistenceAdapter;
import com.example.bankingapp.domain.model.Cuenta;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@PersistenceAdapter
class CuentaPersistAdapter implements CargarCuentaPort {

    private final SpringCuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;

    @Override
    public Cuenta cargarCuenta(String numero) {

        CuentaJpaEntity cuenta =
                cuentaRepository.findByNumero(numero)
                        .orElseThrow(EntityNotFoundException::new);

        return cuentaMapper.mapToDomainEntity(cuenta);

    }

    @Override
    public void actualizarSaldo(String cuentaOrigen, BigDecimal monto) {
        Cuenta cuenta = cargarCuenta(cuentaOrigen);
        CuentaJpaEntity cuentaJpaEntity = cuentaMapper.mapToJpaEntityCuenta(cuenta);
        cuentaJpaEntity.setSaldo(monto);
        cuentaRepository.save(cuentaJpaEntity);
    }
}
