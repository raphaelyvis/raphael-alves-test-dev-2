package com.nitryx.backend.application


import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@CompileStatic
@Component
@Slf4j
class BootstrapRunner implements ApplicationRunner {


    @Transactional
    @Override
    void run(ApplicationArguments args) throws Exception {

    }
}
