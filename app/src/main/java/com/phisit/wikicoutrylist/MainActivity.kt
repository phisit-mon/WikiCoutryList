package com.phisit.wikicoutrylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.phisit.wikicoutrylist.home.HomeScreen
import com.phisit.wikicoutrylist.search.SearchScreen
import com.phisit.wikicoutrylist.ui.theme.WikiCoutryListTheme
import com.phisit.wikicoutrylist.webview.WikiWebViewScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            WikiCoutryListTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {},
                    bottomBar = {
                        WikiBottomNavigationBar(
                            navController = navController,
                            bottomNavItems = BottomNavItems
                        )
                    },
                ) { innerPadding ->
                    NavigationGraph(
                        navController,
                        Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        NavHost(
            navController = navController,
            startDestination = RouteScreen.HomeRouteScreen
        ) {
            composable<RouteScreen.HomeRouteScreen> {
                HomeScreen()
            }

            composable<RouteScreen.SearchRouteScreen> {
                SearchScreen()
            }

            composable<RouteScreen.WebViewRouteScreen> {
                val country = it.toRoute<RouteScreen.WebViewRouteScreen>().country
                WikiWebViewScreen(country.orEmpty())
            }
        }
    }
}

@Composable
fun WikiBottomNavigationBar(
    navController: NavController,
    bottomNavItems: List<BottomNavItem>,
    modifier: Modifier = Modifier
) {
   val navHostBackStackEntry by navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier
    ) {
        bottomNavItems.forEachIndexed { index, item ->

            val isSelected =
                navHostBackStackEntry?.destination?.route == item.route::class.java.canonicalName

            BottomNavigationItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label,
                        tint = if (isSelected) Color.White else Color.Black
                    )
                },
                label = {
                    Text(text = item.label)
                },
                modifier = Modifier.testTag("${item.label}NavItem")
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WikiBottomNavigationBarPreview() {
    WikiBottomNavigationBar(
        navController = rememberNavController(),
        bottomNavItems = BottomNavItems
    )
}

