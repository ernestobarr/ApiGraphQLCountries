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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.di.navigation.moduloroomdinavcompose.api.graphql2.FilmsListQuery
import com.example.di.navigation.moduloroomdinavcompose.ui.theme.ModuloRoomDINavComposeTheme
import com.example.di.navigation.moduloroomdinavcompose.viewmodel.FilmsListViewModel
import kotlinx.coroutines.launch

@Composable
fun FilmsListScreen(viewModel: FilmsListViewModel) {
    // Utiliza el estado para manejar los datos de la lista de películas
    var films by remember { mutableStateOf<List<FilmsListQuery.Film?>?>(null) }
    val coroutineScope = rememberCoroutineScope()

    // LaunchedEffect se utiliza para realizar la consulta cuando el Composable es visible
    LaunchedEffect(key1 = Unit) {
        val fetchedFilms = viewModel.fetchFilmsList()
        films = fetchedFilms
    }

    // Composable principal que muestra la lista de películas
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lista de películas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (films != null) {
            LazyColumn {
                items(films!!) { film ->
                    FilmItem(film)
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
fun FilmItem(film: FilmsListQuery.Film?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = film?.title ?: "Título no disponible",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Director: ${film?.director ?: "Desconocido"}",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "Fecha de lanzamiento: ${film?.releaseDate ?: "Desconocida"}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFilmsListScreen() {
    ModuloRoomDINavComposeTheme {
        FilmsListScreen(FilmsListViewModel())
    }
}