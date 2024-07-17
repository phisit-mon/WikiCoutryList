package com.phisit.wikicoutrylist

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
sealed class RouteScreen {

    @Serializable
    object HomeRouteScreen : RouteScreen()

    @Serializable
    data class WebViewRouteScreen(
        val country: String? = null
    ) : RouteScreen()

    @Serializable
    object SearchRouteScreen : RouteScreen()
}


sealed class BottomNavItem(
    val route: RouteScreen,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String
) {
    object HomeScreen :
        BottomNavItem(
            RouteScreen.HomeRouteScreen,
            Icons.Default.Home,
            Icons.Outlined.Home,
            "Home"
        )

    object SearchScreen :
        BottomNavItem(
            RouteScreen.SearchRouteScreen,
            Icons.Default.Search,
            Icons.Outlined.Search,
            "Search"
        )
}

val BottomNavItems = listOf<BottomNavItem>(
    BottomNavItem.HomeScreen,
    BottomNavItem.SearchScreen
)