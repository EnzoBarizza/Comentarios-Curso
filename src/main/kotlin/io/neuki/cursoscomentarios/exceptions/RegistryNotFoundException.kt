package io.neuki.cursoscomentarios.exceptions

class RegistryNotFoundException : RuntimeException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, innerException: Exception) : super(message, innerException)
}