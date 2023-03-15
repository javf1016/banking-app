package com.example.bankingapp.application.port.in;

import com.example.bankingapp.adapters.outbound.TransferenciaJpaEntity;

public interface TransferirDineroUseCase {
    TransferenciaJpaEntity transferirDinero(TransferirDineroCommand command);

}
