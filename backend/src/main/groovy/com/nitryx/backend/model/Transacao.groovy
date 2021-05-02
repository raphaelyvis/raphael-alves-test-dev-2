package com.nitryx.backend.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent

@Entity
class Transacao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id
    @PastOrPresent
    private Calendar dataHora
    @NotNull
    private BigDecimal valor

    @Deprecated
    Transacao() {
    }

    Transacao(BigDecimal valor) {
        this.dataHora = Calendar.getInstance()
        this.valor = valor
    }

    Long getId() {
        return id
    }

    Calendar getDataHora() {
        return dataHora
    }

    BigDecimal getValor() {
        return valor
    }

    @Override
    String toString() {
        return "Transacao{" +
                "id=" + id +
                ", dataHora=" + dataHora +
                ", valor=" + valor +
                '}'
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Transacao transacao = (Transacao) o

        if (dataHora != transacao.dataHora) return false
        if (id != transacao.id) return false
        if (valor != transacao.valor) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (dataHora != null ? dataHora.hashCode() : 0)
        result = 31 * result + (valor != null ? valor.hashCode() : 0)
        return result
    }
}
