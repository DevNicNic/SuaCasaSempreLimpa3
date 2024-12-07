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
fun RoomTipsScreen (onBackClik: () -> Unit) {
    val defaultImage = painterResource(id = R.drawable.saladicas)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text ("Dicas Para  Sala") },
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
                "Organize primeiro: Recolha objetos espalhados pela sala, como controles remotos, revistas e brinquedos, e guarde-os nos locais apropriados.",
                "Poeira nos móveis: Use um pano macio ou um espanador para remover a poeira de superfícies como mesas, estantes e prateleiras.",
                "Limpeza do sofá: Aspire o sofá ou passe uma escova para remover migalhas e poeira. Se possível, lave capas removíveis ou use um limpador apropriado para o tecido.",
                "Cuidado com o chão: Varra ou aspire o chão para remover sujeira e cabelos. Finalize com um pano úmido e o produto de limpeza adequado ao tipo de piso (madeira, cerâmica, etc.)",
                "Janelas brilhantes: Limpe as janelas com um pano de microfibra e uma mistura de água e vinagre. Se preferir, use produtos próprios para vidros.",
                "Detalhes fazem diferença: Limpe interruptores de luz, maçanetas, rodapés e molduras de portas para deixar a sala ainda mais apresentável.",
                "Aromatize o ambiente: Finalize com um aromatizador de ambiente ou coloque um difusor com óleos essenciais para manter um cheiro agradável na sala.",
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
                            contentDescription ="Imagem Padrão da Sala",
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
fun RoomTipsScreenPreview(){
    RoomTipsScreen( onBackClik = {})

}