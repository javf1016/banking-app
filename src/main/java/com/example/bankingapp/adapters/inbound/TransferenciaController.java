package com.example.bankingapp.adapters.inbound;

import com.example.bankingapp.adapters.outbound.TransferenciaJpaEntity;
import com.example.bankingapp.application.port.in.TransferirDineroCommand;
import com.example.bankingapp.application.port.in.TransferirDineroUseCase;
import com.example.bankingapp.common.WebAdapter;
import com.example.bankingapp.domain.model.Transferencia;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferirDineroUseCase transferirDineroUseCase;

    @PostMapping
    public TransferenciaJpaEntity crearTransferencia(@RequestBody Transferencia transferencia) {

        TransferirDineroCommand command = new TransferirDineroCommand(
                transferencia.getCuentaOrigen(),
                transferencia.getCuentaDestino(),
                transferencia.getMonto());

        TransferenciaJpaEntity transferenciaJpaEntity = transferirDineroUseCase.transferirDinero(command);
        return ResponseEntity.created(URI.create("/transferencias/" + transferenciaJpaEntity.getId())).body(transferenciaJpaEntity).getBody();
    }

}
