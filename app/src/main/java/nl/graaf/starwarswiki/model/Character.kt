package nl.graaf.starwarswiki.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

data class Character(
        val name: String,
        @SerializedName("birth_year")
        val birthYear: String,
        val films: List<String>,
        val vehicles: List<String>,
        @SerializedName("homeworld")
        val homeWorldUrl: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.readString())

    fun getPeriod(): String {
        return birthYear.substring(birthYear.length - 3)
    }

    fun getYear(): Float {
        return (birthYear.replace(getPeriod(), "")).toFloat()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(birthYear)
        parcel.writeStringList(films)
        parcel.writeStringList(vehicles)
        parcel.writeString(homeWorldUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}