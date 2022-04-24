package io.neuki.cursoscomentarios.service

import io.neuki.cursoscomentarios.models.Usuario
import org.springframework.stereotype.Service

@Service
class UsuarioService(private var usuarios: List<Usuario> = ArrayList()) {

    init {
        val usuario1 = Usuario(
            id = 1UL,
            nome = "Enzo Barizza Leal",
            email = "enzobarizza@gmail.com"
        )

        val usuario2 = Usuario(
            id = 2UL,
            nome = "Pedro Cruz Neves",
            email = "pedroneves@gmail.com"
        )

        val usuario3 = Usuario(
            id = 3UL,
            nome = "Maitê Andrade",
            email = "maiteandrade@gmail.com"
        )

        usuarios = listOf(usuario1, usuario2, usuario3)
    }

    fun listas(): List<Usuario> {
        return usuarios
    }

    fun buscarPorId(id: ULong): Usuario? {
        return usuarios.find { it.id == id }
    }
}