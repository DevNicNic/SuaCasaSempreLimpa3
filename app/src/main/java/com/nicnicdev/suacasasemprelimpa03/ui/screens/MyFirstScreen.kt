package com.nicnicdev.suacasasemprelimpa03.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nicnicdev.suacasasemprelimpa03.ui.register.LoginViewModel
import com.nicnicdev.suacasasemprelimpa03.ui.screens.navigation.Screen
import com.nicnicdev.suacasasemprelimpa03.ui.theme.SuaCasaSempreLimpa03Theme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyFirstScreen(navController: NavHostController,
                  viewModel: LoginViewModel = koinViewModel()
) {
    var login by remember { mutableStateOf("") } // estado para login
    var password by remember { mutableStateOf("") } // estado para senha

    val uiState by viewModel.uiState.collectAsState() // estado sa interface do ViewModel

    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween, // deixa o botão no rodapé
            horizontalAlignment = Alignment.CenterHorizontally // ceentraliza o conteúdo horizontalmente
        ) {
            //coluna separada para os textos
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp), // ajusta os espaços entre os textos
                horizontalAlignment = Alignment.CenterHorizontally // centraliza os textos
            ) {
                Text(text = "SUA ", style = MaterialTheme.typography.headlineMedium)
                Text(text = "CASA", style = MaterialTheme.typography.headlineMedium)
                Text(text = "SEMPRE LIMPA", style = MaterialTheme.typography.headlineMedium)
            }
            Spacer(modifier = Modifier.height(20.dp))

            //coluna separada para os campos de login, senha, botão entrar e texto clicavel esqueci a senha
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally //centraliza o botão
            ) {
                //campo login
                OutlinedTextField(
                    value = login,
                    onValueChange = { login = it },
                    label = { Text("Login") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = uiState.error != null
                )
                // campo senha
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(), //para ocultar senha
                    isError = uiState.error != null
                )

                uiState.error?.let { errorMessage ->
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                // texto clicavel "esqueci a senha"
                Text(
                    text = "Esqueci a Senha.",
                    modifier = Modifier
                        .align(Alignment.End) //alinhamento a direita
                        .padding(top = 8.dp) // define o espaço entre o texto e o q esta acima dele
                        .clickable { /*Ação do clique*/ },
                    style = MaterialTheme.typography.bodySmall // estilo do texto
                )
                // botão entrar
                Button(
                    onClick = {
                              viewModel.onLogin(login, password)
                    },
                    modifier = Modifier
                        .padding(top = 26.dp) //largura extra acima do botão
                        .width(200.dp) // largura do botão
                ) {
                    Text(text = "ENTRAR")
                }
            }
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { Log.d("Navigation", "Navegação para tela cadastro")
                         navController.navigate(Screen.Register.route) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally) // deixa o botão centralizado
            ) {
                Text(text = "CADASTRE-SE")
            }
        }
        if (uiState.success){
            LaunchedEffect(Unit) {
                navController.navigate(Screen.Options.route){
                    popUpTo(Screen.Main.route) {inclusive = true}
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyFirstScreenPreview() {
    val navController = rememberNavController()
    SuaCasaSempreLimpa03Theme {
        MyFirstScreen(navController = navController)
    }
}