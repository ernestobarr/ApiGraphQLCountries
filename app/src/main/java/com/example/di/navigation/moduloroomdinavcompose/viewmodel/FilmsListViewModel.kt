package com.example.di.navigation.moduloroomdinavcompose.viewmodel

import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.example.di.navigation.moduloroomdinavcompose.api.graphql2.FilmsListQuery
import okhttp3.OkHttpClient

class FilmsListViewModel : ViewModel() {
    // Configura una instancia de ApolloClient (si no lo has hecho ya)
    val apolloClient = ApolloClient.builder()
        .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
        .build()

    // Define una función suspendida para realizar la consulta GraphQL
    suspend fun fetchFilmsList(): List<FilmsListQuery.Film?>? {
        try {
            val query = FilmsListQuery()
            val response = apolloClient.query(query).await()
            if (response.data != null) {
                return response.data?.allFilms?.films
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}