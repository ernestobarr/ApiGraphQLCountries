package com.example.di.navigation.moduloroomdinavcompose.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apollographql.apollo.api.Response
import com.example.di.navigation.moduloroomdinavcompose.api.graphql3.PokemonListQuery
import com.example.di.navigation.moduloroomdinavcompose.api.graphql4.RickMortyCharacterQuery
import com.example.di.navigation.moduloroomdinavcompose.viewmodel.PokemonListViewModel
import com.example.di.navigation.moduloroomdinavcompose.viewmodel.RickMortyViewModel

@Composable
fun RickMortyScreen(viewModel: RickMortyViewModel) {
    var characters by remember { mutableStateOf<List<RickMortyCharacterQuery.Result>?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        val fetchedCharacters = viewModel.fetchRickMortyCharacters()
        characters = fetchedCharacters
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lista de Personajes de Rick and Morty",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (characters != null) {
            LazyColumn {
                items(characters!!) { character ->
                    CharacterItem(character)
                }
            }
        } else {
            // Muestra un indicador de carga o un mensaje de error
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun CharacterItem(character: RickMortyCharacterQuery.Result?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Nombre: ${character?.name}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "Estado: ${character?.status}",
                fontSize = 16.sp
            )
            Text(
                text = "Especie: ${character?.species}",
                fontSize = 16.sp
            )
        }
    }
}