package nl.frankkie.catdex.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import nl.frankkie.catdex.R
import nl.frankkie.catdex.model.Breed
import nl.frankkie.catdex.model.CAT_BREED_DEFAULT_IMAGE
import nl.frankkie.catdex.model.FAKE_BREED
import nl.frankkie.catdex.ui.theme.CatDexTheme


@Composable
fun CatBreedList(
    items: List<Breed>,
    isRefreshing: Boolean = true,
    onRefresh: () -> Unit,
    onSelectBreed: (Breed?) -> Unit
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh
    ) {
        if (items.isNotEmpty() && !isRefreshing) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(items.size) { itemIndex ->
                    ListItem({ CatBreedListItem(items[itemIndex]) },
                        modifier = Modifier.clickable {
                            onSelectBreed(items[itemIndex])
                        })
                    HorizontalDivider()
                }
            }
        } else {
            // Show loading state
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun CatBreedListItem(breed: Breed) {
    // TODO: Hardcoded height, move to dimens or theme.
    Row(Modifier.height(72.dp)) {
        Box(
            Modifier.height(72.dp).width(100.dp),
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
        Spacer(Modifier.width(8.dp))
        Column(
            Modifier
                .weight(1f)
                .fillMaxHeight(), // take rest of the width
            verticalArrangement = Arrangement.Center
        ) {
            Text(breed.name, style = MaterialTheme.typography.labelLarge)
            Text(breed.temperament ?: "", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
fun CatBreedListItemPreview() {
    CatDexTheme {
        CatBreedListItem(FAKE_BREED)
    }
}