package com.example.di.navigation.moduloroomdinavcompose.viewmodel

import android.util.Log
import android.util.Log.d
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import com.example.di.navigation.moduloroomdinavcompose.api.graphql.LaunchDetailsQuery
import com.example.di.navigation.moduloroomdinavcompose.api.graphql.LaunchDetailsQuery.Launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TwelveGraphQLViewModel  @Inject constructor(
    savedStateHandle: SavedStateHandle,
)  : ViewModel(){

    //Reference:https://www.apollographql.com/docs/kotlin/v2/essentials/get-started-kotlin
    //Reference:https://www.apollographql.com/docs/kotlin/v2/tutorial/00-introduction

    val defaultMission= LaunchDetailsQuery.Mission(name="",missionPatch="")
    val defaultLaunch=Launch(id="",site="",mission=defaultMission)

    val _data=MutableStateFlow<Launch>(defaultLaunch)
    val data=_data.asStateFlow()

    val apolloClient = ApolloClient.builder()
        .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
        .build()

    fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                //apolloClient.query(LaunchDetailsQuery(id = "83")).toDeferred().await()
                val result=apolloClient.query(LaunchDetailsQuery(id = "83")).toDeferred().await()
                val launch = result.data?.launch
                if (launch == null || result.hasErrors()) {
                    Log.d(TAG,"Error  on result")
                }else{
                    result.data?.launch?.let {
                        _data.value=it
                    }
                }
            } catch (e: ApolloException) {
                Log.d(TAG,"General error", e)
            }
        }
    }

    companion object{
        const val TAG="TwelveGraphQLViewModel"
    }

}