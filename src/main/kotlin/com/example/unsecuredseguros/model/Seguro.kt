package com.example.unsecuredseguros.model

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.LocalDateTime
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "seguros")
data class Seguro(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSeguro")
    val idSeguro: Long = 0,

    @NotNull
    @Column(length = 10)
    var nif: String,

    @NotNull
    @Column(length = 100)
    var nombre: String,

    @NotNull
    @Column(length = 100)
    var ape1: String,

    @Column(length = 100)
    var ape2: String? = null,

    @Min(18)
    @Column(nullable = false)
    var edad: Int,

    @Min(0)
    @NotNull
    @Column(name = "num_hijos")
    var numHijos: Int,

    @NotNull
    @Column(name = "fecha_creacion")
    var fechaCreacion: LocalDateTime,

    @NotNull
    @Column(length = 10)
    var sexo: String,

    @NotNull
    @Column
    var casado: Boolean,

    @NotNull
    @Column
    var embarazada: Boolean
)
