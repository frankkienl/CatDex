package nl.frankkie.catdex.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

const val CAT_BREED_DEFAULT_IMAGE = "https://cdn2.thecatapi.com/images/h19-vtIeX.jpg"

val FAKE_BREED = Breed(
    id = "cat",
    name = "Cat breed",
    lifeSpan = "7-9",
    temperament = "Calm, but moody at times",
    origin = "Anywhere",
    description = "Generic cat",
    history = "Cats have been around for years",
    referenceImageId = "cat",
    image = BreedImage(
        id = "cat",
        url = CAT_BREED_DEFAULT_IMAGE,
        width = 512,
        height = 512
    ),
    weight = BreedWeight(
        imperial = "10-20",
        metric = "10-20"
    ),
    height = BreedHeight(
        imperial = "10-20",
        metric = "10-20"
    )
)

@Serializable
data class Breed(
    val id: String,
    val name: String,
    @SerialName("life_span")
    val lifeSpan: String? = null,
    val temperament: String? = null,
    val origin: String? = null,
    val description: String? = null,
    val history: String? = null,
    @SerialName("reference_image_id")
    val referenceImageId: String? = null,
    val image: BreedImage? = null,
    val weight: BreedWeight? = null,
    val height: BreedHeight? = null
)

@Serializable
data class BreedImage(
    val id: String? = null,
    val url: String? = null,
    val width: Int? = null,
    val height: Int? = null
)

@Serializable
data class BreedWeight(
    val imperial: String? = null,
    val metric: String? = null
)

@Serializable
data class BreedHeight(
    val imperial: String? = null,
    val metric: String? = null
)