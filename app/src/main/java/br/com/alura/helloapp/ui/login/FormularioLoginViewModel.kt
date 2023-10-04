package br.com.alura.helloapp.ui.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import br.com.alura.helloapp.data.User
import br.com.alura.helloapp.database.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FormularioLoginViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val userDao: UserDao,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FormularioLoginUiState())
    val uiState: StateFlow<FormularioLoginUiState>
        get() = _uiState.asStateFlow()

    init {
        _uiState.update { state ->
            state.copy(
                onNomeMudou = {
                    _uiState.value = _uiState.value.copy(
                        nome = it
                    )
                },
                onUsuarioMudou = {
                    _uiState.value = _uiState.value.copy(
                        usuario = it
                    )
                },
                onSenhaMudou = {
                    _uiState.value = _uiState.value.copy(
                        senha = it
                    )
                }
            )
        }
    }

    suspend fun salvaLogin() {
        userDao.insert(
            User(
                userId = _uiState.value.usuario, password = _uiState.value.senha, userName = _uiState.value.nome
            )
        )
    }
}