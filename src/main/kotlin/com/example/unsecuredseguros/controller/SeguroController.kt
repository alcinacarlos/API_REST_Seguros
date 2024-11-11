package com.example.unsecuredseguros.controller

import com.example.unsecuredseguros.model.Seguro
import com.example.unsecuredseguros.service.SeguroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/seguros")
class SeguroController() {

    @Autowired
    private lateinit var seguroService: SeguroService

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: String?
    ): Seguro? {

        if (id.isNullOrEmpty()) return null

        return seguroService.getById(id)
    }

    @GetMapping
    fun getAll(): List<Seguro> {
        return seguroService.getAll()
    }

    @PostMapping
    fun insert(
        @RequestBody seguro: Seguro?
    ): Any {
        if (seguro == null) return "Introduce un seguro"
        return seguroService.insert(seguro)
    }

    @DeleteMapping("/{id}")
    fun deleteById(
        @PathVariable id: String?
    ): Boolean {
        if (id.isNullOrEmpty()) return false
        return seguroService.deleteById(id)
    }
    
    @PutMapping
    fun update(
        @RequestBody seguro: Seguro?
    ):Any{
        if (seguro == null) return "Introduce un seguro"
        return seguroService.update(seguro)
    }


}