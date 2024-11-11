package com.example.unsecuredseguros.utils

import com.example.unsecuredseguros.model.Seguro

object Utilidades {
    fun checkDni(dni: String): Boolean {
        val dniRegex = Regex("^[0-9]{8}[A-Z]$")
        if (!dniRegex.matches(dni)) return false

        val dniNumeros = dni.substring(0, 8).toIntOrNull() ?: return false
        val dniLetra = dni.last()
        val letras = "TRWAGMYFPDXBNJZSQVHLCKE"

        return dniLetra == letras[dniNumeros % 23]
    }

    fun validarSeguro(seguro: Seguro): String? {
        if (!Utilidades.checkDni(seguro.nif)) return "El NIF no es válido"
        if (seguro.nombre.isEmpty()) return "El nombre no puede estar vacío"
        if (seguro.ape1.isEmpty()) return "El primer apellido no puede estar vacío"
        if (seguro.edad <= 0) return "La edad debe ser mayor que 0"
        if (seguro.edad in 0..17) return "No es posible ser menor de edad para hacer un seguro"
        if (seguro.sexo.isEmpty()) return "El campo sexo no puede ser null"
        if (seguro.numHijos < 0) return "El número de hijos no puede ser menor que 0"
        if (!seguro.casado && seguro.numHijos != 0) return "Si no está casado, el número de hijos debe ser 0"
        if (seguro.embarazada && seguro.sexo != "Mujer") return "Si está embarazada, el sexo debe ser 'Mujer'"

        return null  // Todos los campos son válidos
    }

}