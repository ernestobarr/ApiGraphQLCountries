package com.example.di.navigation.moduloroomdinavcompose.viewmodel

import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.example.di.navigation.moduloroomdinavcompose.api.graphql5.CountriesQuery

class CountriesViewModel : ViewModel() {

    private val apolloClient = ApolloClient.builder()
        .serverUrl("https://countries.trevorblades.com/graphql")
        .build()

    suspend fun fetchCountriesData(): Response<CountriesQuery.Data>? {
        try {
            val query = CountriesQuery()
            val response = apolloClient.query(query).await()
            return response
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}