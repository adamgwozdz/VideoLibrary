package com.example.videolibrary.local.trendingtvseries

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class TvSeriesModel : RealmObject {
    @PrimaryKey
    var id: Long = 0L
    var adult: Boolean = false
    var backdropPath: String? = ""
    var name: String = ""
    var originalLanguage: String = ""
    var originalName: String = ""
    var overview: String = ""
    var posterPath: String? = ""
    var mediaType: String = ""
    var genreIds: RealmList<Int> = realmListOf()
    var popularity: Double = 0.0
    var firstAirDate: String = ""
    var voteAverage: Double = 0.0
    var voteCount: Int = 0
    var originCountry: RealmList<String> = realmListOf()

    // local
    var tag: String = ""
}