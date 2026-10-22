package nl.frankkie.catdex.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import nl.frankkie.catdex.R
import nl.frankkie.catdex.model.Breed
import nl.frankkie.catdex.model.CAT_BREED_DEFAULT_IMAGE
import nl.frankkie.catdex.model.FAKE_BREED
import nl.frankkie.catdex.ui.theme.CatDexTheme
import nl.frankkie.catdex.ui.viewmodel.AppViewModel

@Composable
fun DetailScreen(
    appViewModel: AppViewModel,
    navController: NavController
) {

    val uiState by appViewModel.uiState.collectAsState()
    val breed = uiState.selectedBreed

    // No breed, no details to show
    if (breed == null) {
        navController.popBackStack()
        return
    }

    Scaffold(
        topBar = {
            DetailScreenTopBar(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            CatDetails(breed)
        }
    }
}


@Composable
fun CatDetails(breed: Breed) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(Modifier.fillMaxWidth()) {
            // Big image on top
            Box(
                Modifier.heightIn(min = 72.dp, max = 300.dp),
                contentAlignment = Alignment.Center
            ) {
                if (LocalInspectionMode.current) {
                    Image(
                        painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier.clip(MaterialTheme.shapes.medium)
                    )
                } else {
                    AsyncImage(
                        model = breed.image?.url ?: CAT_BREED_DEFAULT_IMAGE,
                        contentDescription = null,
                        modifier = Modifier.clip(MaterialTheme.shapes.medium)
                    )
                }
            }
        }


        Text(breed.name, style = MaterialTheme.typography.displayLarge)

        breed.temperament?.let {
            Text(
                breed.temperament,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(4.dp)
            )
        }

        Row(Modifier.fillMaxWidth()) {
            breed.lifeSpan?.let {
                CatDetailBox(Modifier.weight(1f), "Lifespan", breed.lifeSpan)
            }
            breed.height?.metric?.let {
                CatDetailBox(Modifier.weight(1f), "Height", breed.height.metric)
            }
            breed.weight?.metric?.let {
                CatDetailBox(Modifier.weight(1f), "Weight", breed.weight.metric)
            }
            // more than 3 won't fit nicely
//            breed.origin?.let {
//                CatDetailBox(Modifier.weight(1f), "Origin", breed.origin)
//            }
        }

        breed.description?.let { Text(breed.description, modifier = Modifier.padding(4.dp)) }

    }
}


@Composable
fun CatDetailBox(modifier: Modifier, label: String, text: String) {
    Box(
        modifier
            .height(72.dp)
            .padding(4.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.medium)
    ) {
        Column(Modifier
            .fillMaxSize()
            .padding(8.dp)) {
            Text(label)
            Text(text)
        }
    }
}

@Preview
@Composable
fun CatDetailBoxPreview() {
    CatDexTheme {
        CatDetailBox(Modifier, "Detail", "Text")
    }
}


@Preview
@Composable
fun CatDetailsPreview() {
    CatDexTheme {
        CatDetails(FAKE_BREED)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenTopBar(modifier: Modifier = Modifier, onNavigateBack: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier,
            ) {
                Text(stringResource(R.string.app_name))
            }
        }
    )
}


@Preview
@Composable
fun DetailScreenTopBarPreview() {
    CatDexTheme {
        DetailScreenTopBar(onNavigateBack = {})
    }
}