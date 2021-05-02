package com.nitryx.backend.service

import com.nitryx.backend.exceptions.SaldoNegativoException
import com.nitryx.backend.model.Conta
import com.nitryx.backend.repository.ContaRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Slf4j
class Transferencia {

    @Autowired
    ContaRepository contaRepository

    void transferir(Long contaOrigemId, Long contaDestinoId, BigDecimal valor) {

        Conta contaOrigem = contaRepository.findById(contaOrigemId).get()
        Conta contaDestino = contaRepository.findById(contaDestinoId).get()

        try {
            contaOrigem.sacar(valor)
            contaDestino.depositar(valor)
            log.info(String.format("Transferência no valor de %.2f realizada da %s para a %s", valor, contaOrigem, contaDestino))
        } catch (SaldoNegativoException e) {
            System.out.println(e.getMessage())
        }
    }
}
