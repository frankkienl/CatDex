package nl.frankkie.catdex.data

import nl.frankkie.catdex.model.Breed
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {

    /**
     * Get list of breeds
     * Example Output (using curl):
     * curl -H "x-api-key: API_KEY" "https://api.thecatapi.com/v1/breeds/?page=1&limit=1"
     * [{"id":"aege","name":"Aegean","species_id":"1","life_span":"9-12","temperament":"Affectionate, Social, Intelligent, Playful, Active, Adaptable","origin":"Greece","country_codes":"GR","country_code":"GR","description":"Medium-sized natural breed with a semi-longhaired coat, muscular build, and almond-shaped eyes. One of the oldest domesticated cat breeds, known for its love of water and fishing heritage.","bred_for":null,"perfect_for":null,"breed_group":"Semi-longhair","history":"Native to the Greek islands known as the Cyclades in the Aegean Sea. These are natural cats that developed without selective breeding. Common in Greece but rare internationally. Considered one of the oldest domesticated cat breeds.","reference_image_id":"h19-vtIeX","weight":{"imperial":"9-12","metric":"4-5.4"},"height":{"imperial":"9-11","metric":"23-28"},"image":{"id":"h19-vtIeX","url":"https://cdn2.thecatapi.com/images/h19-vtIeX.jpg","width":4272,"height":2848}}]
     */

    @GET("breeds/")
    fun listBreeds(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 12
    ): Call<List<Breed>>
}