package com.example.bankingapp.domain;

import com.example.bankingapp.adapters.outbound.CuentaJpaEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;

public class CuentaJpaEntityTest {

    @Test
    public void givenTwoCuentasWithSameNumeroAndSaldo_whenEquals_thenReturnTrue() {
        // given
        String numeroCuenta = "123456";
        BigDecimal saldo = BigDecimal.TEN;
        CuentaJpaEntity cuenta1 = new CuentaJpaEntity(null, numeroCuenta, saldo);
        CuentaJpaEntity cuenta2 = new CuentaJpaEntity(null, numeroCuenta, saldo);

        // then
        Assert.assertTrue(cuenta1.equals(cuenta2));
    }

    @Test
    public void givenTwoCuentasWithDifferentNumero_whenEquals_thenReturnFalse() {
        // given
        String numeroCuenta1 = "123456";
        String numeroCuenta2 = "789012";
        BigDecimal saldo = BigDecimal.TEN;
        CuentaJpaEntity cuenta1 = new CuentaJpaEntity(null, numeroCuenta1, saldo);
        CuentaJpaEntity cuenta2 = new CuentaJpaEntity(null, numeroCuenta2, saldo);

        // then
        Assert.assertFalse(cuenta1.equals(cuenta2));
    }

    @Test
    public void givenTwoCuentasWithDifferentSaldo_whenEquals_thenReturnFalse() {
        // given
        String numeroCuenta = "123456";
        BigDecimal saldo1 = BigDecimal.TEN;
        BigDecimal saldo2 = BigDecimal.ONE;
        CuentaJpaEntity cuenta1 = new CuentaJpaEntity(null, numeroCuenta, saldo1);
        CuentaJpaEntity cuenta2 = new CuentaJpaEntity(null, numeroCuenta, saldo2);

        // then
        Assert.assertFalse(cuenta1.equals(cuenta2));
    }

    @Test
    public void givenCuenta_whenToString_thenReturnStringRepresentation() {
        // given
        String numeroCuenta = "123456";
        BigDecimal saldo = BigDecimal.TEN;
        CuentaJpaEntity cuenta = new CuentaJpaEntity(null, numeroCuenta, saldo);

        // then
        Assertions.assertEquals("CuentaJpaEntity(id=null, numero=123456, saldo=10)", cuenta.toString());
    }
}
