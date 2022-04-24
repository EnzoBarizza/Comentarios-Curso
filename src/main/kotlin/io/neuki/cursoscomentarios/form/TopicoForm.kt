package io.neuki.cursoscomentarios.form

import io.neuki.cursoscomentarios.models.Topico
import io.neuki.cursoscomentarios.service.CursoService
import io.neuki.cursoscomentarios.service.UsuarioService
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TopicoForm(
    @field:NotEmpty @field:Size(min = 5, max = 20) val titulo: String,
    @field:NotEmpty val mensagem: String,
    @field:NotNull val idCurso: ULong,
    @field:NotNull val idAutor: ULong
) {
    fun convertToTopico(cursoService: CursoService, usuarioService: UsuarioService, currentId: ULong): Topico {
        val curso = cursoService.buscarPorId(idCurso) ?: throw IllegalArgumentException("Curso não encontrado")
        val autor = usuarioService.buscarPorId(idAutor) ?: throw IllegalArgumentException("Usuario não encontrado")

        return Topico(
            id = currentId + 1UL,
            titulo = titulo,
            mensagem = mensagem,
            curso = curso,
            autor = autor
        )
    }
}