package io.neuki.cursoscomentarios.controller

import io.neuki.cursoscomentarios.exceptions.RegistryNotFoundException
import io.neuki.cursoscomentarios.form.TopicoForm
import io.neuki.cursoscomentarios.form.TopicoPut
import io.neuki.cursoscomentarios.service.TopicoService
import io.neuki.cursoscomentarios.view.TopicoView
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping
    fun get(): List<TopicoView> {
        return service.listas()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: ULong): ResponseEntity<TopicoView> {
        val topico = service.buscarPorId(id)

        return if (topico != null) ResponseEntity<TopicoView>(topico, HttpStatus.OK) else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: ULong): ResponseEntity<Unit> {
        return try {
            service.deleteTopico(id)
            ResponseEntity<Unit>(HttpStatus.OK)
        } catch (e: RegistryNotFoundException) {
            ResponseEntity<Unit>(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun put(@RequestBody @Valid topicoPut: TopicoPut, @PathVariable id: ULong): ResponseEntity<TopicoView> {
        return try {
            val topico = service.editTopico(topicoPut, id)
            ResponseEntity<TopicoView>(topico, HttpStatus.OK)
        } catch (e: RegistryNotFoundException) {
            ResponseEntity<TopicoView>(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun post(@RequestBody @Valid topicoForm: TopicoForm): ResponseEntity<TopicoView> {
        val topico = service.novoTopico(topicoForm)

        return ResponseEntity<TopicoView>(topico, HttpStatus.OK)
    }
}