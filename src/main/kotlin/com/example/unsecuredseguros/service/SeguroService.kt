package com.example.unsecuredseguros.service

import com.example.unsecuredseguros.model.Seguro
import com.example.unsecuredseguros.repository.ISeguroRepository
import com.example.unsecuredseguros.utils.Utilidades
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SeguroService() {

    @Autowired
    private lateinit var iSeguroRepository: ISeguroRepository

    fun getById(id: String): Seguro? {
        val idL = id.toLongOrNull() ?: return null

        return iSeguroRepository.findByIdOrNull(idL)
    }

    fun deleteById(id: String): Boolean {
        val idL = id.toLongOrNull() ?: return false
        try {
            iSeguroRepository.deleteById(idL)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }
    fun getAll(): List<Seguro> {
        return iSeguroRepository.findAll()
    }

    fun insert(seguro: Seguro): Any {
        val seguroCheckeado = Utilidades.validarSeguro(seguro)
        if (seguroCheckeado != null) return seguroCheckeado

        return try {
            iSeguroRepository.save(seguro)
        }catch (e:Exception){
            e.printStackTrace()
            "Error al insertar el seguro"
        }
    }

    fun update(seguro: Seguro): Any {
        val seguroCheckeado = Utilidades.validarSeguro(seguro)
        if (seguroCheckeado != null) return seguroCheckeado

        val seguroExistente = iSeguroRepository.findByIdOrNull(seguro.idSeguro) ?: return "ID del seguro no exite"


        seguroExistente.nombre = seguro.nombre
        seguroExistente.ape1 = seguro.ape1
        seguroExistente.ape2 = seguro.ape2
        seguroExistente.edad = seguro.edad
        seguroExistente.numHijos = seguro.numHijos
        seguroExistente.fechaCreacion = seguro.fechaCreacion
        seguroExistente.sexo = seguro.sexo
        seguroExistente.casado = seguro.casado
        seguroExistente.embarazada = seguro.embarazada

        return try {
            iSeguroRepository.save(seguro)
        }catch (e:Exception){
            e.printStackTrace()
            "Error al insertar el seguro"
        }
    }
}