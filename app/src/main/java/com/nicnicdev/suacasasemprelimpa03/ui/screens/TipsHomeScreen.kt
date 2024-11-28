package com.nicnicdev.suacasasemprelimpa03.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicnicdev.suacasasemprelimpa03.R







@Composable
fun TipsHomeScreen(userName: String, onCategoryClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            //saudação no topo da tela
            Text(
                text = "Olá $userName, Bem vindo ao Dicas Sua Casa Sempre Limpa",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        item {
            // grade com categorias organizadas em 2 linhas
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                //linha superior com 3 categorias
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    categories.take(3).forEach { category ->
                        CategoryCard(
                            category = category,
                            onClick = { onCategoryClick(category.name) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                //linha inferior com 2 categorias
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    categories.drop(3).forEach { category ->
                        CategoryCard(
                            category = category, onClick = { onCategoryClick(category.name) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun CategoryCard(category: Category, onClick: () -> Unit, modifier: Modifier){

    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .clickable { onClick() },
        elevation = androidx.compose.material3.CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // aqui vai uma imagem representativa
            Image(
                painter = painterResource(id = category.imageRes) ,
                contentDescription = category.name,
                modifier = Modifier
                    .size(200.dp)
                    .clickable { onClick() } //icone tb clicavel
            )
           // Spacer(modifier = Modifier.height(.dp))
            //titulo da categoria
            Text(
                text = category.name,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.clickable { onClick() } // titulo também é clicável
            )

        }
    }
}



data class Category(val name: String,@DrawableRes val imageRes: Int)

val categories= listOf(

    Category("Cozinha", R.drawable.cozinha),
    Category("Sala", R.drawable.sala),
    Category("Banheiro", R.drawable.banheiro),
    Category("Quarto", R.drawable.quarto),
    Category("Lavanderia", R.drawable.lavanderia),
)

@Preview(showBackground = true)
@Composable
fun TipsHomeScreenPreview() {
    TipsHomeScreen(userName = "Nicole", onCategoryClick = {})
}