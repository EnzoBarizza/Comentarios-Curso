package io.neuki.cursoscomentarios.service

import io.neuki.cursoscomentarios.exceptions.RegistryNotFoundException
import io.neuki.cursoscomentarios.form.TopicoForm
import io.neuki.cursoscomentarios.form.TopicoPut
import io.neuki.cursoscomentarios.models.Curso
import io.neuki.cursoscomentarios.models.Topico
import io.neuki.cursoscomentarios.models.Usuario
import io.neuki.cursoscomentarios.view.TopicoView
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
    private var topicos: MutableList<Topico> = ArrayList<Topico>(),
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
) {

    init {
        val topico = Topico(
            id = 1UL,
            titulo = "Duvida kotlin",
            mensagem = "",
            curso = Curso(
                id = 1UL,
                nome = "Kotlin",
                categoria = "Programação"
            ),
            autor = Usuario(
                id = 1UL,
                nome = "Enzo Barizza",
                email = "enzobarizza@gmail.com"
            )
        )

        val topico2 = Topico(
            id = 2UL,
            titulo = "Duvida java",
            mensagem = "",
            curso = Curso(
                id = 2UL,
                nome = "Java",
                categoria = "Programação"
            ),
            autor = Usuario(
                id = 1UL,
                nome = "Enzo Barizza",
                email = "enzobarizza@gmail.com"
            )
        )

        val topico3 = Topico(
            id = 3UL,
            titulo = "Duvida C#",
            mensagem = "",
            curso = Curso(
                id = 3UL,
                nome = "C#",
                categoria = "Programação"
            ),
            autor = Usuario(
                id = 1UL,
                nome = "Enzo Barizza",
                email = "enzobarizza@gmail.com"
            )
        )

        topicos = mutableListOf(topico, topico2, topico3)
    }

    fun listas(): List<TopicoView> {
        return topicos.stream().map {
            it.convertToTopicoView()
        }.collect(Collectors.toList())
    }

    fun buscarPorId(id: ULong): TopicoView? {
        val topico = topicos.find { it.id == id } ?: return null

        return topico.convertToTopicoView()
    }

    fun novoTopico(topicoForm: TopicoForm): TopicoView {
        val topico = topicoForm.convertToTopico(
            usuarioService = usuarioService,
            cursoService = cursoService,
            currentId = topicos.size.toULong()
        )

        topicos.add(topico)

        return topico.convertToTopicoView()
    }

    fun editTopico(topicoPut: TopicoPut, id: ULong): TopicoView {
        val topico = topicos.find { it.id == id } ?: throw RegistryNotFoundException("O topico com o id $id não foi encontrado")

        topico.editFieldsUsingTopicoPut(topicoPut)

        return topico.convertToTopicoView()
    }

    fun deleteTopico(id: ULong) {
        if(!topicos.removeIf { it.id == id }) throw RegistryNotFoundException("O topico com o id $id não foi encontrado")
    }
}