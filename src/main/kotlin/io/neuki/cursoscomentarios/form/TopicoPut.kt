package io.neuki.cursoscomentarios.form

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class TopicoPut(
    @field:NotEmpty @field:Size(min = 5, max = 20) val titulo: String,
    @field:NotEmpty val mensagem: String
)