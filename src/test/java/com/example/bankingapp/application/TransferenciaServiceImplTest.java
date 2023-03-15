package com.example.bankingapp.application;

import com.example.bankingapp.adapters.outbound.*;
import com.example.bankingapp.application.port.in.TransferirDineroCommand;
import com.example.bankingapp.application.port.out.CargarCuentaPort;
import com.example.bankingapp.domain.exception.CuentaNotFoundException;
import com.example.bankingapp.domain.model.Cuenta;
import com.example.bankingapp.domain.service.impl.TransferenciaServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.math.BigDecimal;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TransferenciaServiceImplTest {

    @InjectMocks
    private TransferenciaServiceImpl transferenciaService;

    @Mock
    private TransferenciaRepository transferenciaRepository;

    @Mock
    private SpringCuentaRepository cuentaRepository;

    @Mock
    private CargarCuentaPort cargarCuentaPort;

    @Mock
    private CuentaMapper cuentaMapper;

    @Test
    public void transferirDinero_SaldoSuficiente_TransferenciaExitosa() {
        // Arrange
        String cuentaOrigen = "123456789";
        String cuentaDestino = "987654321";
        BigDecimal monto = BigDecimal.valueOf(100);

        CuentaJpaEntity cuentaOrigenJpa = new CuentaJpaEntity(1L, cuentaOrigen, BigDecimal.valueOf(200));
        CuentaJpaEntity cuentaDestinoJpa = new CuentaJpaEntity(2L, cuentaDestino, BigDecimal.valueOf(50));

        Mockito.when(cuentaRepository.findByNumero(cuentaOrigen)).thenReturn(Optional.of(cuentaOrigenJpa));
        Mockito.when(cuentaRepository.findByNumero(cuentaDestino)).thenReturn(Optional.of(cuentaDestinoJpa));

        Cuenta cuentaOrigenDomain = new Cuenta(cuentaOrigen, BigDecimal.valueOf(200));
        Cuenta cuentaDestinoDomain = new Cuenta(cuentaDestino, BigDecimal.valueOf(50));

        Mockito.when(cuentaMapper.mapToDomainEntity(cuentaOrigenJpa)).thenReturn(cuentaOrigenDomain);
        Mockito.when(cuentaMapper.mapToDomainEntity(cuentaDestinoJpa)).thenReturn(cuentaDestinoDomain);

        Mockito.doNothing().when(cargarCuentaPort).actualizarSaldo(eq(cuentaOrigen), any(BigDecimal.class));
        Mockito.doNothing().when(cargarCuentaPort).actualizarSaldo(eq(cuentaDestino), any(BigDecimal.class));

        // Act
        TransferirDineroCommand command = new TransferirDineroCommand(cuentaOrigen, cuentaDestino, monto);
        TransferenciaJpaEntity result = transferenciaService.transferirDinero(command);

        // Assert
        Assert.assertNotNull(result);
        Assertions.assertEquals(cuentaOrigen, result.getCuentaOrigen());
        Assertions.assertEquals(cuentaDestino, result.getCuentaDestino());
        Assertions.assertEquals(monto, result.getMonto());
        Mockito.verify(transferenciaRepository, Mockito.times(1)).save(result);
    }

    @Test(expected = CuentaNotFoundException.class)
    public void transferirDineroCuentaNoExiste() {
        // given
        String cuentaOrigen = "123456";
        String cuentaDestino = "234567";
        BigDecimal saldoOrigen = new BigDecimal("100.00");
        BigDecimal monto = new BigDecimal("150.00");
        TransferirDineroCommand command = new TransferirDineroCommand(cuentaOrigen, cuentaDestino, monto);
        transferenciaService.transferirDinero(command);

    }

}
