package nl.frankkie.catdex

import kotlinx.serialization.json.Json
import nl.frankkie.catdex.model.Breed
import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun testJsonDecodingWithNullsAndMissingImage() {
        val jsonString = """
            [
              {
                "id":"bamb",
                "name":"Bambino",
                "species_id":"1",
                "life_span":"12-14",
                "temperament":"Affectionate, Lively",
                "origin":"United States",
                "description":"Small, hairless cat",
                "history":"Created in early 2000s",
                "reference_image_id":"Oaoo1ky3A",
                "weight":{"imperial":"5-9","metric":"2.3-4"},
                "height":{"imperial":"6-8","metric":"15-20"},
                "image":{"id":"Oaoo1ky3A","url":"https://cdn2.thecatapi.com/images/Oaoo1ky3A.jpg","width":3878,"height":2771}
              },
              {
                "id":"braz",
                "name":"Brazilian Shorthair",
                "species_id":"1",
                "life_span":"14-20",
                "temperament":"Active, Playful",
                "origin":"Brazil",
                "description":"Medium-sized, elegant cat",
                "history":"Natural breed",
                "reference_image_id":null,
                "weight":{"imperial":"8-18","metric":"3.6-8.2"},
                "height":{"imperial":"9-11","metric":"23-28"}
              }
            ]
        """.trimIndent()

        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        val breeds = json.decodeFromString<List<Breed>>(jsonString)
        assertNotNull(breeds)
        assertEquals(2, breeds.size)
        assertEquals("bamb", breeds[0].id)
        assertNotNull(breeds[0].image)
        assertEquals("braz", breeds[1].id)
        assertNull(breeds[1].image)
        assertNull(breeds[1].referenceImageId)
    }
}
