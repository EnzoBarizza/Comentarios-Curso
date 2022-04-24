package io.neuki.cursoscomentarios.view

import io.neuki.cursoscomentarios.models.StatusTopico
import java.time.LocalDateTime

data class TopicoView(
    val id: ULong?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime
)