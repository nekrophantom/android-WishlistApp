package dev.nekro.wishlistapp.presentation.navigation

sealed class Route (val route: String) {
    object HomeScreen: Route("home_screen")
    object CreateScreen: Route("create_screen")

}