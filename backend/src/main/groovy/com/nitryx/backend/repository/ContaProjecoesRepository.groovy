package com.nitryx.backend.repository

import com.nitryx.backend.controller.dto.ContaComCliente
import com.nitryx.backend.controller.dto.TransacaoComConta
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ContaProjecoesRepository {

    @Autowired
    JdbcTemplate template

    List<ContaComCliente> contasComCliente() {

        def list = new ArrayList<>()

        def rs = template.queryForList(
                "select" +
                    " con.id as contaId" +
                    ", cli.nome as nomeCliente" +
                    ", con.numero_agencia as numeroAgencia" +
                    ", con.numero_conta as numeroConta" +
                    ", con.saldo as saldo" +
                " from " +
                    " conta con" +
                    " join cliente cli on con.cliente_id = cli.id"
        )

        for(Map row : rs) {
            def contaComCliente = new ContaComCliente()

            contaComCliente.setContaId(row.get("contaId"))
            contaComCliente.setNomeCliente(row.get("nomeCliente"))
            contaComCliente.setNumeroAgencia(row.get("numeroAgencia"))
            contaComCliente.setNumeroConta(row.get("numeroConta"))
            contaComCliente.setSaldo(row.get("saldo"))

            list.add(contaComCliente)
        }

        return list

    }

    List<TransacaoComConta> transacoes(Long id) {

        def list = new ArrayList<>()

        def rs = template.queryForList(
                "select" +
                    " con.id as contaId" +
                    ", tr.data_hora as dataHoraTransacao" +
                    ", tr.valor as valorTransacao" +
                " from " +
                    " conta_transacoes ct" +
                    " join conta con on ct.conta_id = con.id" +
                    " join transacao tr on ct.transacoes_id = tr.id" +
                " where con.id = ?", id
        )

        for(Map row : rs) {
            def transacao = new TransacaoComConta()

            transacao.setContaId(row.get("contaId"))
            transacao.setDataHoraTransacao(row.get("dataHoraTransacao"))
            transacao.setValorTransacao(row.get("valorTransacao"))

            list.add(transacao)
        }

        return list
    }
}
