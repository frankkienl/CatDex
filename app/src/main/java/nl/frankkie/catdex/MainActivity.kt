package nl.frankkie.catdex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import nl.frankkie.catdex.ui.App
import nl.frankkie.catdex.ui.theme.CatDexTheme

// Trying to keep the Activity stuff to a minimum,
// this should make it easier to port to CMP if needed.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatDexTheme {
                App()
            }
        }
    }
}