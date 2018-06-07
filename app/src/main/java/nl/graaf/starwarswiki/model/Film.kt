package nl.graaf.starwarswiki.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

data class Film(
        val title: String,
        @SerializedName("episode_id")
        val episode: Int,
        @SerializedName("opening_crawl")
        val openingCrawl: String,
        val director: String,
        val producer: String,
        @SerializedName("release_date")
        val releaseDate: String,
        val characters: List<String>,
        val vehicles: List<String>
): Serializable