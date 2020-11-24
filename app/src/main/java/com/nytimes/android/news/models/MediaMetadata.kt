package com.nytimes.android.news.models


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MediaMetadata(
    @SerializedName("format")
    val format: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readString(),
        source.readValue(Int::class.java.classLoader) as Int?
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(format)
        writeValue(height)
        writeString(url)
        writeValue(width)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MediaMetadata> =
            object : Parcelable.Creator<MediaMetadata> {
                override fun createFromParcel(source: Parcel): MediaMetadata = MediaMetadata(source)
                override fun newArray(size: Int): Array<MediaMetadata?> = arrayOfNulls(size)
            }
    }
}