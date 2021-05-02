package com.nitryx.backend.controller

import com.nitryx.backend.repository.ContaProjecoesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contas")
class ProjecoesController {

    @Autowired
    ContaProjecoesRepository contaProjecoesRepository

    @GetMapping
    List listarContas() {
        return contaProjecoesRepository.contasComCliente()
    }

    @GetMapping("/{id}/transacoes")
    List listarTransacoes(@PathVariable("id") Long id) {
        return contaProjecoesRepository.transacoes(id)
    }
}
