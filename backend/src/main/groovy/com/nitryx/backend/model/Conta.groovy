package com.nitryx.backend.model

import com.nitryx.backend.exceptions.SaldoNegativoException
import org.springframework.util.StringUtils

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id
    @NotBlank
    private String numeroAgencia
    @NotBlank
    private String numeroConta
    @NotNull
    private BigDecimal saldo
    @OneToOne(cascade = CascadeType.ALL)
    private Cliente cliente
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transacao> transacoes

    @Deprecated
    Conta() {
    }

    Conta(String numeroAgencia, String numeroConta, Cliente cliente) {
        this.numeroAgencia = numeroAgencia
        this.numeroConta = numeroConta
        this.saldo = 0
        this.cliente = cliente
        this.transacoes = new ArrayList<>()
    }

    Long getId() {
        return id
    }

    String getNumeroAgencia() {
        return numeroAgencia
    }

    String getNumeroConta() {
        return numeroConta
    }

    BigDecimal getSaldo() {
        return saldo
    }

    Cliente getCliente() {
        return cliente
    }

    List<Transacao> getTransacoes() {
        return transacoes
    }

    private void setTransacoes(Transacao transacao) {
        this.transacoes.add(transacao)
        this.saldo = getSaldo() + transacao.getValor()
    }

    void depositar(BigDecimal valor) {
        if (isValido(valor))
            setTransacoes(new Transacao(valor))
    }

    void sacar(BigDecimal valor) {
        if (isValido(valor)) {
            if (valor > getSaldo())
                throw new SaldoNegativoException("O saldo não pode ser negativo")

            valor = valor * -1
            setTransacoes(new Transacao(valor))
        }
    }

    private static Boolean isValido(BigDecimal valor) {
        if (valor <= 0 || StringUtils.isEmpty(valor)) {
            println "Valor não permitido para a transação"
            return false
        }
        return true
    }

    @Override
    String toString() {
        return "Conta Bancaria {" +
                "agência: '" + numeroAgencia + '\'' +
                ", número: '" + numeroConta + '\'' +
                ", cliente: " + cliente.getNome() +
                '}'
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Conta conta = (Conta) o

        if (id != conta.id) return false
        if (numeroAgencia != conta.numeroAgencia) return false
        if (numeroConta != conta.numeroConta) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (numeroAgencia != null ? numeroAgencia.hashCode() : 0)
        result = 31 * result + (numeroConta != null ? numeroConta.hashCode() : 0)
        return result
    }

}
