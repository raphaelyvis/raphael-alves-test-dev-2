package com.nitryx.backend.repository


import com.nitryx.backend.model.Conta
import org.springframework.data.jpa.repository.JpaRepository

interface ContaRepository extends JpaRepository<Conta, Long>{

}