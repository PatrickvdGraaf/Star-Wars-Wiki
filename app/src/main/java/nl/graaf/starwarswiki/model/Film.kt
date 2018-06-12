package nl.graaf.starwarswiki.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

@Entity(tableName = "filmsData")
data class Film(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        @ColumnInfo(name = "title") val title: String = "",
        @Json(name = "episode_id")
        @ColumnInfo(name = "episode") val episode: Int = 0
) : Serializable