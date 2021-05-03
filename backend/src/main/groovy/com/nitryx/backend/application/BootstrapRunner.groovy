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

        contaRaphael.depositar(BigDecimal.valueOf(10))
        contaMaria.depositar(BigDecimal.valueOf(25))

        contaMaria.sacar(BigDecimal.valueOf(24.99 as double))

        transferencia.transferir(contaRaphael.getId(), contaMaria.getId(), BigDecimal.valueOf(5))

    }

    private void criarConta(Conta conta) {
        if (!StringUtils.isEmpty(conta)) {
            contaRepository.save(conta)
            log.info(String.format("%s criada na base de dados!", conta))
        }
    }
}
