package br.com.alura.helloapp.ui.userDialog

import br.com.alura.helloapp.data.User

data class GerenciaUsuariosUiState(
    val usuarios: List<User> = emptyList(),
)