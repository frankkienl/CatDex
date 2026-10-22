package nl.frankkie.catdex.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import nl.frankkie.catdex.data.CAT_API_KEY
import nl.frankkie.catdex.data.CatRepository
import nl.frankkie.catdex.ui.screen.MainScreen
import nl.frankkie.catdex.ui.theme.CatDexTheme
import nl.frankkie.catdex.ui.viewmodel.AppViewModel

@Composable
fun App() {
    val catRepository = CatRepository(
        getCatApiKey(),
//        if (BuildConfig.DEBUG) {
        if (true) {
            CatRepository.BASE_URL_BETA
        } else {
            CatRepository.BASE_URL_PROD
        }
    )
    val appViewModel = viewModel { AppViewModel(catRepository) }

    Box(modifier = Modifier.fillMaxSize()) {
        MainScreen(appViewModel)
    }
}

fun getCatApiKey() : String {
    // In the ideal world,
    // I would use Google Play Integrity API, to check the validity of this app,
    // Then, using a backend, provide the API key, only after validation.
    // But this is not the ideal world, so the API key is hardcoded and inside the APK
    // So if needed, we just disable the API key in the Cat API Dashboard,
    // rotate the key, send out new APK... Worst case, we get rate-limited.
    return CAT_API_KEY // TODO: Hard-coded key
}

@Preview
@Composable
fun PreviewApp() {
    CatDexTheme {
        App()
    }
}