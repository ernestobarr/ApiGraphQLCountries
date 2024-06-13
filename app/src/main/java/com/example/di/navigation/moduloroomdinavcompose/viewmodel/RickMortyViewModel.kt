package com.example.di.navigation.moduloroomdinavcompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.example.di.navigation.moduloroomdinavcompose.api.graphql2.FilmsListQuery
import com.example.di.navigation.moduloroomdinavcompose.api.graphql4.RickMortyCharacterQuery

class RickMortyViewModel : ViewModel() {
    // Configura una instancia de ApolloClient
    val apolloClient = ApolloClient.builder()
        .serverUrl("https://rickandmortyapi.com/graphql")
        .build()

    // Define una función suspendida para realizar la consulta GraphQL
    suspend fun fetchRickMortyCharacters(): List<RickMortyCharacterQuery.Result>? {
        try {
            val query = RickMortyCharacterQuery()
            val response = apolloClient.query(query).await()
            if (response.data != null) {
                return response.data!!.characters?.results?.filterNotNull()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}