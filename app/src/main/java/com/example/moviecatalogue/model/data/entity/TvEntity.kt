package com.example.moviecatalogue.model.data.entity
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tv_entity")
data class TvEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="id")
    val id: Int,
    @ColumnInfo(name = "name")
    val title: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "first_air_date")
    val date: String,
    @ColumnInfo(name = "fav")
    var fav: Boolean = false
)