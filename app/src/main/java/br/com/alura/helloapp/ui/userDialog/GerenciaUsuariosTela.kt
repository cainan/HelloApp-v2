package br.com.alura.helloapp.ui.userDialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.helloapp.R
import br.com.alura.helloapp.data.User
import br.com.alura.helloapp.ui.components.AsyncImagePerfil
import br.com.alura.helloapp.ui.theme.HelloAppTheme

@Composable
fun GerenciaUsuariosTela(
    state: GerenciaUsuariosUiState,
    modifier: Modifier = Modifier,
    onClickAbreDetalhes: (String) -> Unit = {},
    onClickVolta: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            AppBarGerenciaUsuarios(
                onClickVolta = onClickVolta
            )
        }
    ) { paddingValues ->

        LazyColumn(modifier.padding(paddingValues)) {
            items(state.usuarios) { usuario ->
                UsuarioGerenciaItem(usuario) { nomeUsuario ->
                    onClickAbreDetalhes(nomeUsuario)
                }
            }
        }

    }
}

@Composable
fun AppBarGerenciaUsuarios(
    onClickVolta: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.gerenciar_usuarios))
        },
        navigationIcon = {
            IconButton(
                onClick = onClickVolta
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.voltar)
                )
            }
        })
}

@Composable
fun UsuarioGerenciaItem(
    user: User,
    onClick: (String) -> Unit,
) {
    Card(
        Modifier.clickable { onClick(user.userId) },
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            Modifier.padding(16.dp),
        ) {
            AsyncImagePerfil(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Column(
                Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = user.userName,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = user.userId
                )
            }
        }
    }
}


@Preview
@Composable
fun AppBarGerenciaUsuariosPreview() {
    HelloAppTheme {
        AppBarGerenciaUsuarios()
    }
}

@Preview
@Composable
fun BuscaContatosPreview() {
    HelloAppTheme {
        GerenciaUsuariosTela(
            state = GerenciaUsuariosUiState(
                usuarios = listOf(
                    User(
                        userId = "User Id Teste",
                        userName = "User Name Teste"
                    )
                )
            )
        )
    }
}
