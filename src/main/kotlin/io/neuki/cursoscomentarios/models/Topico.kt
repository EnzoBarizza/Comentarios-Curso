package io.neuki.cursoscomentarios.models

import io.neuki.cursoscomentarios.form.TopicoPut
import io.neuki.cursoscomentarios.view.TopicoView
import java.time.LocalDateTime

data class Topico(
    val id: ULong? = null,
    var titulo: String,
    var mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val curso: Curso,
    val autor: Usuario,
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    val respostas: List<Resposta> = ArrayList()
) {
    fun convertToTopicoView(): TopicoView {
        return TopicoView(
            id = id,
            titulo = titulo,
            mensagem = mensagem,
            status = status,
            dataCriacao = dataCriacao
        )
    }

    fun editFieldsUsingTopicoPut(topico: TopicoPut) {
        titulo = topico.titulo
        mensagem = topico.mensagem
    }
}