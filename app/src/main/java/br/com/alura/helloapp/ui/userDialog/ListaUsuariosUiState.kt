package br.com.alura.helloapp.ui.userDialog

import br.com.alura.helloapp.data.User

data class ListaUsuariosUiState(
    val nomeDeUsuario: String? = null,
    val nome: String? = null,
    val anotherUsers: List<User> = emptyList(),
)