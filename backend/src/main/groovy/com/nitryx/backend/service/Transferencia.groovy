package com.nitryx.backend.service

import com.nitryx.backend.exceptions.SaldoNegativoException
import com.nitryx.backend.model.Conta
import com.nitryx.backend.repository.ContaRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

@Service
@Slf4j
class Transferencia {

    @Autowired
    ContaRepository contaRepository

    void transferir(Long contaOrigemId, Long contaDestinoId, BigDecimal valor) {

        Conta contaOrigem = null
        Conta contaDestino = null

        if (isValido(valor)) {
            if (exists(contaOrigemId) && exists(contaDestinoId)) {
                contaOrigem = contaRepository.findById(contaOrigemId).orElse()
                contaDestino = contaRepository.findById(contaDestinoId).orElse()
            }
        }

        if (exists(contaOrigem) && exists(contaDestino)) {
            try {
                contaOrigem.sacar(valor)
                contaDestino.depositar(valor)
                log.info(String.format("Transferência no valor de %.2f realizada da %s para a %s", valor, contaOrigem, contaDestino))
            } catch (SaldoNegativoException e) {
                System.out.println(e.getMessage())
            }
        }
    }

    private static Boolean exists(Conta conta) {
        return !StringUtils.isEmpty(conta)
    }

    private static Boolean exists(Long id) {
        return id != null
    }

    private static Boolean isValido(BigDecimal valor) {
        if(valor <=0 || StringUtils.isEmpty(valor)) {
            println "Valor não permitido para a transação"
            return false
        }
        return true
    }
}
