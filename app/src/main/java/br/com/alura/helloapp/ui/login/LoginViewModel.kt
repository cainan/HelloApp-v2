package br.com.alura.helloapp.ui.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import br.com.alura.helloapp.database.UserDao
import br.com.alura.helloapp.preferences.PreferencesKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val userDao: UserDao,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(
                onUsuarioMudou = {
                    _uiState.value = _uiState.value.copy(
                        usuario = it
                    )
                },
                onSenhaMudou = {
                    _uiState.value = _uiState.value.copy(
                        senha = it
                    )
                },
            )
        }
    }

    suspend fun tentaLogar() {

        val searchedUser = userDao.buscaPorId(_uiState.value.usuario).first()

        if (searchedUser != null &&
            searchedUser.password == _uiState.value.senha
        ) {
            dataStore.edit {
                it[PreferencesKey.LOGADO] = true
                it[PreferencesKey.LOGGED_USER] = _uiState.value.usuario
            }
            logaUsuario()
        } else {
            exibeErro()
        }
    }

    private fun exibeErro() {
        _uiState.value = _uiState.value.copy(
            exibirErro = true
        )
    }

    private fun logaUsuario() {
        _uiState.value = _uiState.value.copy(
            logado = true
        )
    }
}

