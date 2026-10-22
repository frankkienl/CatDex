package nl.frankkie.catdex.ui.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nl.frankkie.catdex.data.CatRepository
import nl.frankkie.catdex.model.Breed
import okio.IOException

// I rather not use `ViewModel` here, it's not supported on CMP
// (See code below for a CMP version)
class AppViewModel(
    val catRepository: CatRepository
) : ViewModel() {

    // I heard there is some new syntax available for this;
    // Removing the needs for a separate underscore variable. Fix later, if time available.
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun fetchBreeds(page: Int = 1, limit: Int = 12) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                val breeds = catRepository.listBreeds(page, limit)

                // update UI state
                breeds?.let { safeBreeds ->
                    _uiState.value = _uiState.value.copy(breeds = safeBreeds, isRefreshing = false)
                }
            }
        }
    }

    fun selectBreed(breed: Breed?) {
        // update UI state
        _uiState.value = _uiState.value.copy(selectedBreed = breed)
    }

}

data class AppUiState(
    val breeds: List<Breed> = emptyList<Breed>(),
    val isRefreshing: Boolean = true,
    val selectedBreed: Breed? = null
)


// CMP version

class AppViewModelCmp(scope: CoroutineScope) {
    // Not implemented; As this project will be Android-only
}

interface ViewModelFactory {
    @Composable
    fun provideViewModel(scope: CoroutineScope): AppViewModelCmp
}

object ViewModelFactoryImpl : ViewModelFactory {

    private lateinit var instance: AppViewModelCmp
    private var isInitialized = false

    @Composable
    override fun provideViewModel(scope: CoroutineScope): AppViewModelCmp {
        if (!isInitialized) {
            instance = AppViewModelCmp(
                scope = scope
            )
            isInitialized = true
        }
        return instance
    }

}