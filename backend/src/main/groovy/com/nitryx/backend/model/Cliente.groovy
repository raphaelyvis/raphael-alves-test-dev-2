package com.nitryx.backend.model

import org.hibernate.validator.constraints.br.CPF

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id
    @NotBlank @Column(unique = true)
    @CPF
    private String cpf
    @NotBlank
    private String nome

    @Deprecated
    Cliente(){

    }

    Cliente(String cpf, String nome) {
        this.cpf = cpf
        this.nome = nome
    }

    Long getId() {
        return id
    }

    String getCpf() {
        return cpf
    }

    String getNome() {
        return nome
    }

    @Override
    String toString() {
        return "Cliente{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                '}'
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Cliente cliente = (Cliente) o

        if (cpf != cliente.cpf) return false
        if (id != cliente.id) return false
        if (nome != cliente.nome) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0)
        result = 31 * result + (nome != null ? nome.hashCode() : 0)
        return result
    }
}
