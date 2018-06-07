package nl.graaf.starwarswiki.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
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
        @SerializedName("episode_id")
        @ColumnInfo(name = "episode") val episode: Int = 0
) : Serializable