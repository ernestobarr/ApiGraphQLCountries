package com.example.di.navigation.moduloroomdinavcompose.compose


import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.composable
import com.example.di.navigation.moduloroomdinavcompose.api.graphql.LaunchDetailsQuery
import com.example.di.navigation.moduloroomdinavcompose.viewmodel.*


interface MyDestination {
    val icon: ImageVector
    val route: String
}

object TwelveGraphQL : MyDestination {
    override val icon = Icons.Filled.PieChart
    override val route = "TwelveGraphQL"
}


// Adds destination screen to `this` NavGraphBuilder
fun NavGraphBuilder.TwelveDestination(
    // Navigation events are exposed to the caller to be handled at a higher level
    onNavigateToList: () -> Unit= {}
) {
    composable(route=TwelveGraphQL.route) {
        // The ViewModel as a screen level state holder produces the screen
        // UI state and handles business logic for the ConversationScreen
        val viewModel: TwelveGraphQLViewModel = hiltViewModel<TwelveGraphQLViewModel>()
        val uiState: State<LaunchDetailsQuery.Launch> = viewModel.data.collectAsStateWithLifecycle()
        TwelveGraphqlScreen(
            uiState.value,
            onLoad = viewModel::getData,
            onNavigateToList
        )
    }
}


object MainGraph:MyDestination{
    override val icon = Icons.Filled.PieChart
    override val route = "MainGraph"

}

fun NavGraphBuilder.mainGraph(navController: NavHostController) {

    TwelveDestination()


}



fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
