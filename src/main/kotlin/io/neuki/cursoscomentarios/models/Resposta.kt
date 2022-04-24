package io.neuki.cursoscomentarios.models

import java.time.LocalDateTime

data class Resposta(
    val id: ULong? = null,
    val mensagem: String,
    val dataCriacao: LocalDateTime,
    val autor: Usuario,
    val topico: Topico,
    val solucao: Boolean
)
