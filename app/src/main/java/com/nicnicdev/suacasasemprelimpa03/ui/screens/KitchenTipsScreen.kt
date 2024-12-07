package com.nicnicdev.suacasasemprelimpa03.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicnicdev.suacasasemprelimpa03.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitchenTipsScreen (onBackClik: () -> Unit) {
    val defaultImage = painterResource(id = R.drawable.dicas)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text ("Dicas para Cozinha")},
                navigationIcon = {
                    IconButton(onClick = onBackClik) {
                        Icon(Icons.Default.ArrowBack,contentDescription = "Voltar ")

                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(listOf(
                "Limpe as bancadas após cada uso.",
                "Lave a louça imediatamente após as refeições.",
                "Esvazie o lixo diariamente para evitar odores.",
                "Use bicarbonato de sódio para remover manchas.",
                "Limpe as bancadas após cada uso.",
                "Lave a louça imediatamente após as refeições.",
                "Esvazie o lixo diariamente para evitar odores.",
                "Use bicarbonato de sódio para remover manchas.",
                "Verifique periodicamente a geladeira para eliminar alimentos vencidos.",
                "Mantenha os utensílios organizados para facilitar o uso."
            )) {
                tip ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ){
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = defaultImage,
                            contentDescription ="Imagem Padrão da Dicas Cozinha",
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = tip,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.weight(1f) // faz o texto opcupar o espaço restante


                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KitchenTipsScreenPreview(){
    KitchenTipsScreen( onBackClik = {})

}