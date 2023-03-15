package com.example.bankingapp.application.port.out;

import com.example.bankingapp.domain.model.Cuenta;

import java.math.BigDecimal;


public interface CargarCuentaPort {

    Cuenta cargarCuenta(String numero);

    public void actualizarSaldo(String cuentaOrigen, BigDecimal monto);
}
