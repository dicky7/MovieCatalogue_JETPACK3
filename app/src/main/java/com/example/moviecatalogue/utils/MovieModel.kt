package com.example.moviecatalogue.utils
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize
import kotlin.reflect.KProperty

@Parcelize
class  MovieModel (
    var id: String,
    var title: String,
    var desc: String,
    var genre: String,
    var realese: String,
    var poster: Int
):Parcelable

