package io.neuki.cursoscomentarios.service

import io.neuki.cursoscomentarios.models.Curso
import org.springframework.stereotype.Service

@Service
class CursoService(private var cursos: List<Curso> = ArrayList()) {
    init {
        val curso1 = Curso(
            id = 1UL,
            nome = "Kotlin",
            categoria = "Programação"
        )

        val curso2 = Curso(
            id = 2UL,
            nome = "Java",
            categoria = "Programação"
        )

        val curso3 = Curso(
            id = 3UL,
            nome = "C#",
            categoria = "Programação"
        )

        cursos = listOf(curso1, curso2, curso3)
    }

    fun listas(): List<Curso> {
        return cursos
    }

    fun buscarPorId(id: ULong): Curso? {
        return cursos.find { it.id == id }
    }
}