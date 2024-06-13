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
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apollographql.apollo.api.Response
import com.example.di.navigation.moduloroomdinavcompose.api.graphql3.PokemonListQuery
import com.example.di.navigation.moduloroomdinavcompose.viewmodel.FilmsListViewModel
import com.example.di.navigation.moduloroomdinavcompose.viewmodel.PokemonListViewModel

@Composable
fun PokemonListScreen(viewModel: PokemonListViewModel)  {
    // Utiliza el estado para manejar los datos de la lista de Pokémon
    var pokemonList by remember { mutableStateOf<Response<PokemonListQuery.Data>?>(null) }
    val coroutineScope = rememberCoroutineScope()

    // LaunchedEffect se utiliza para realizar la consulta cuando el Composable es visible
    LaunchedEffect(key1 = Unit) {
        val fetchedPokemonList = viewModel.fetchPokemonList()
        pokemonList = fetchedPokemonList
    }

    // Composable principal que muestra la lista de Pokémon
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lista de Pokémon",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (pokemonList != null && pokemonList!!.data != null) {
            val pokemons = pokemonList!!.data!!.pokemon_v2_pokemon
            LazyColumn {
                items(pokemons) { pokemon ->
                    PokemonItem(pokemon)
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
fun PokemonItem(pokemon: PokemonListQuery.Pokemon_v2_pokemon?) {
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
                text = "Nombre: ${pokemon?.name}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = "Altura: ${pokemon?.height}",
                fontSize = 16.sp
            )
        }
    }
}