package com.example.di.navigation.moduloroomdinavcompose.viewmodel

import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.example.di.navigation.moduloroomdinavcompose.api.graphql2.FilmsListQuery
import com.example.di.navigation.moduloroomdinavcompose.api.graphql3.PokemonListQuery

class PokemonListViewModel : ViewModel() {
    // Configura una instancia de ApolloClient (si no lo has hecho ya)
    val apolloClient = ApolloClient.builder()
        .serverUrl("https://beta.pokeapi.co/graphql/v1beta")
        .build()

    // Define una función suspendida para realizar la consulta GraphQL
    suspend fun fetchPokemonList(): Response<PokemonListQuery.Data>? {
        try {
            val query = PokemonListQuery()
            val response = apolloClient.query(query).await()
            if (response.data != null) {
                return response
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}