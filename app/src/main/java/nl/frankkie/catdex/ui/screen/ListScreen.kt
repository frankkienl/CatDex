package nl.frankkie.catdex.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.frankkie.catdex.R
import nl.frankkie.catdex.ui.NavDestination
import nl.frankkie.catdex.ui.component.CatBreedList
import nl.frankkie.catdex.ui.theme.CatDexTheme
import nl.frankkie.catdex.ui.viewmodel.AppViewModel

@Composable
fun ListScreen(appViewModel: AppViewModel, navController: NavController) {

    val uiState by appViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        // Do initial refresh
        appViewModel.fetchBreeds(1, 12)
    }

    Scaffold(
        topBar = { ListScreenTopBar() }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            CatBreedList(
                items = uiState.breeds,
                isRefreshing = uiState.isRefreshing,
                onRefresh = { appViewModel.fetchBreeds(1, 12) },
                onSelectBreed = {
                    appViewModel.selectBreed(it)
                    navController.navigate(NavDestination.DetailScreen.route)
                }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreenTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier,
            ) {
                Icon(
                    painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(stringResource(R.string.app_name))
            }
        }
    )
}


@Preview
@Composable
fun ListScreenTopBarPreview() {
    CatDexTheme {
        ListScreenTopBar()
    }
}