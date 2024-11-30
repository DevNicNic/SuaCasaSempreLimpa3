package com.nicnicdev.suacasasemprelimpa03.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicnicdev.suacasasemprelimpa03.data.CategoryDataSource
import com.nicnicdev.suacasasemprelimpa03.domain.model.Category


@Composable
fun TipsHomeScreen(userName: String, onCategoryClick: (String) -> Unit) {
    val categories = CategoryDataSource().getCategories()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        //saudação no topo da tela
        Text(
            text = "Olá $userName, Bem vindo ao Dicas Sua Casa Sempre Limpa",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .padding(top = 30.dp) // Espaço no topo para afastar o texto da borda
        )

        Spacer(modifier = Modifier.height(16.dp)) // Define 16dp de espaço (ou ajuste conforme necessário)

        //Grade com intens
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), //duas colunas
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            //Adiciona todos os itens da lista
            items(categories.size) { index ->
                val category = categories[index]
                CategoryCard(
                    category = category,
                    onClick = { onCategoryClick(category.name) }
                )
            }
        }
    }
}
// grade com categorias organizadas em 2 linhas

@Composable
fun CategoryCard(category: Category, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .width(150.dp) //reduz a largura dos cards
            .aspectRatio(1f) // formato quadrado
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(130.dp)// aumenta o tamanho sa imagem
                    .padding(bottom = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = category.imageRes),
                    contentDescription = "Imagem representativa",
                    modifier = Modifier
                        .size(150.dp) // ajuste tamanho imagem
                )
            }
            // nome da categoria
            Text(
                text = category.name,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 20.sp),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TipsHomeScreenPreview() {
    TipsHomeScreen(userName = "Nicole", onCategoryClick = {})
}
