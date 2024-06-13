package com.example.di.navigation.moduloroomdinavcompose.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import com.example.di.navigation.moduloroomdinavcompose.api.graphql.LaunchDetailsQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
internal fun  TwelveGraphqlScreen (uiState: LaunchDetailsQuery.Launch, onLoad: () -> Unit,
                                   onNavigateTo: () -> Unit= {}
)  {
    Column {
        Button(onClick = { onLoad() }) {
            Text(text = "TwelveGraphqlScreen. OnLoad with ViewModel ")
        }

        Text(text = "Id" )
        Text(text = uiState.id )
    }


}