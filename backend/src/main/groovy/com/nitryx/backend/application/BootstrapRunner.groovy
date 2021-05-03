package com.nitryx.backend.application


import com.nitryx.backend.model.Cliente
import com.nitryx.backend.model.Conta
import com.nitryx.backend.repository.ContaRepository
import com.nitryx.backend.service.Transferencia
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@CompileStatic
@Component
@Slf4j
class BootstrapRunner implements ApplicationRunner {

    @Autowired
    ContaRepository contaRepository
    @Autowired
    Transferencia transferencia

    @Transactional
    @Override
    void run(ApplicationArguments args) throws Exception {

        def contaRaphael = new Conta("1234",
                "333",
                new Cliente("16587937063", "Raphael"))

        def contaMaria = new Conta("1234",
                "444",
                new Cliente("62556741007", "Maria"))

        criarConta(contaRaphael)
        criarConta(contaMaria)

        depositar(contaRaphael, BigDecimal.valueOf(10))
        depositar(contaMaria, BigDecimal.valueOf(25))

        sacar(contaMaria, BigDecimal.valueOf(24.99 as double))

        transferencia.transferir(contaRaphael.getId(), contaMaria.getId(), BigDecimal.valueOf(5))

    }

    private void criarConta(Conta conta) {
        if (exists(conta)) {
            contaRepository.save(conta)
            log.info(String.format("%s criada na base de dados!", conta))
        }
    }

    private static void depositar(Conta conta, BigDecimal valor) {
        if (exists(conta)) {
            conta.depositar(valor)
            log.info(String.format("Depósito de %.2f relizado na %s", valor, conta))
        }
    }

    private static void sacar(Conta conta, BigDecimal valor) {
        if (exists(conta)) {
            conta.sacar(valor)
            log.info(String.format("Saque de %.2f relizado na %s", valor, conta))
        }
    }

    private static Boolean exists(Conta conta) {
        return !StringUtils.isEmpty(conta)
    }
}
