package com.nytimes.android.news.models


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity (tableName = "newsModel")
data class NewsModel(
    @SerializedName("abstract")
    val abstractTxt: String?,
    @SerializedName("adx_keywords")
    val adxKeywords: String?,
    @SerializedName("asset_id")
    val assetId: Long?,
    @SerializedName("byline")
    val byline: String?,
    @SerializedName("column")
    val column: String?,
    @PrimaryKey
    @SerializedName("id")
    val id: Long?,
    @SerializedName("media")
    val media: List<Media>?,
    @SerializedName("nytdsection")
    val nytdsection: String?,
    @SerializedName("published_date")
    val publishedDate: String?,
    @SerializedName("section")
    val section: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("subsection")
    val subsection: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updated")
    val updated: String?,
    @SerializedName("uri")
    val uri: String?,
    @SerializedName("url")
    val url: String?
) : Parcelable {

    fun getThumbnail(): String? {
        if (media?.size!! > 0) {
            if (media.get(0).mediaMetadata?.size!! > 0)
                return media.get(0).mediaMetadata?.get(0)?.url
        }
        return ""
    }

    fun getImage(): String? {
        if (media?.size!! > 0) {
            if (media.get(0).mediaMetadata?.size!! > 2)
                return media.get(0).mediaMetadata?.get(2)?.url
        }
        return ""
    }

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readValue(Long::class.java.classLoader) as Long?,
        source.readString(),
        source.readString(),
        source.readValue(Long::class.java.classLoader) as Long?,
        ArrayList<Media>().apply { source.readList(this, Media::class.java.classLoader) },
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(abstractTxt)
        writeString(adxKeywords)
        writeValue(assetId)
        writeString(byline)
        writeString(column)
        writeValue(id)
        writeList(media)
        writeString(nytdsection)
        writeString(publishedDate)
        writeString(section)
        writeString(source)
        writeString(subsection)
        writeString(title)
        writeString(type)
        writeString(updated)
        writeString(uri)
        writeString(url)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<NewsModel> = object : Parcelable.Creator<NewsModel> {
            override fun createFromParcel(source: Parcel): NewsModel = NewsModel(source)
            override fun newArray(size: Int): Array<NewsModel?> = arrayOfNulls(size)
        }
    }
}