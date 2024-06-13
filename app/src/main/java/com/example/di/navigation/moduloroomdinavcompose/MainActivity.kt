package com.example.di.navigation.moduloroomdinavcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.di.navigation.moduloroomdinavcompose.compose.*
import com.example.di.navigation.moduloroomdinavcompose.ui.theme.ModuloRoomDINavComposeTheme
import com.example.di.navigation.moduloroomdinavcompose.viewmodel.CountriesViewModel
import com.example.di.navigation.moduloroomdinavcompose.viewmodel.FilmsListViewModel
import com.example.di.navigation.moduloroomdinavcompose.viewmodel.PokemonListViewModel
import com.example.di.navigation.moduloroomdinavcompose.viewmodel.RickMortyViewModel
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : ComponentActivity() {
    private val viewModel = CountriesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModuloRoomDINavComposeTheme {
                CountriesScreen(viewModel)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    ModuloRoomDINavComposeTheme {
        CountriesScreen(CountriesViewModel())
    }
}