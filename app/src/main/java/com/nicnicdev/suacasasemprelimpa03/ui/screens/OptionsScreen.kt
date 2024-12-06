package com.nicnicdev.suacasasemprelimpa03.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nicnicdev.suacasasemprelimpa03.R
import com.nicnicdev.suacasasemprelimpa03.ui.screens.navigation.Screen


@Composable
fun ButtonWithImage(
    textButton: String,
    descriptionImage: String,
    resourceImage: Int,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(0.dp) // remove o padding interno do botão
        ) {
            Text(text = textButton)
        }
        Spacer(modifier = Modifier.height(1.dp)) //espaçamento entre o botão e a imagem

        Image(
            painter = painterResource(id = resourceImage),
            contentDescription = descriptionImage,
            modifier = Modifier
                .size(180.dp) //tamanho da imagem
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun OptionsScreen(navController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonWithImage(
            textButton = "Casa Sempre Limpa Dicas",
            descriptionImage = "Imagem para Dicas",
            resourceImage = R.drawable.imagem1,
            onClick = {navController.navigate(Screen.TipsHome.route) }
        )
        ButtonWithImage(
            textButton = "Agendar Tarefas Diárias",
            descriptionImage = "Imagem para Agendar Tarefas Diárias",
            resourceImage = R.drawable.imagem2,
            onClick = {navController.navigate(Screen.Shedule.route) }
        )

        ButtonWithImage(
            textButton = "Agendar Tarrefas Semanais",
            descriptionImage = "Imagem para Agendar Tarefas Semanais",
            resourceImage = R.drawable.imagem3,
            onClick = {/*ação botão*/ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OptionsScreenPreview() {
    OptionsScreen(navController = rememberNavController())
}