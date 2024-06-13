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
import com.apollographql.apollo.api.Response
import com.example.di.navigation.moduloroomdinavcompose.api.graphql5.CountriesQuery
import com.example.di.navigation.moduloroomdinavcompose.viewmodel.CountriesViewModel

@Composable
fun CountriesScreen(viewModel: CountriesViewModel) {
    // Utiliza el estado para manejar los datos de la lista de países
    var countriesData by remember { mutableStateOf<Response<CountriesQuery.Data>?>(null) }
    val coroutineScope = rememberCoroutineScope()

    // LaunchedEffect se utiliza para realizar la consulta cuando el Composable es visible
    LaunchedEffect(key1 = Unit) {
        val fetchedCountriesData = viewModel.fetchCountriesData()
        countriesData = fetchedCountriesData
    }

    // Composable principal que muestra la lista de países
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lista de Continentes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (countriesData != null && countriesData!!.data != null) {
            val continents = countriesData!!.data!!.continents

            // Muestra continentes
            Text("Continentes:")
            continents.forEach { continent ->
                ContinentItem(continent)
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
fun ContinentItem(continent: CountriesQuery.Continent) {
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
                text = "Nombre: ${continent.code}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}