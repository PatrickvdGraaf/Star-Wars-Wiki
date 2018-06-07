package nl.graaf.starwarswiki.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

@Entity(tableName = "vehicleData")
data class Vehicle(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        @ColumnInfo(name = "name") val name: String = "",
        @ColumnInfo(name = "model") val model: String = "",
        @ColumnInfo(name = "length") val length: Float = 1F
): Serializable