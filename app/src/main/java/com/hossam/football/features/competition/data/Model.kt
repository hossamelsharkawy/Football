package com.hossam.football.features.competition.data

import com.google.gson.annotations.SerializedName

class CompetitionsResponse(
    @SerializedName("competitions") var competitions: List<CompetitionDto> = arrayListOf(),
)


data class Area(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("flag") var flag: String? = null,
)

data class CurrentSeason(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("startDate") var startDate: String ="",
    @SerializedName("endDate") var endDate: String="",
    @SerializedName("currentMatchday") var currentMatchday: Int? = null,
    @SerializedName("winner") var winner: Winner? = null,
)

data class CompetitionDto(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("area") var area: Area? = Area(),
    @SerializedName("name") var name: String,
    @SerializedName("code") var code: String? = null,
    @SerializedName("type") var type: String="",
    @SerializedName("emblem") var emblem: String="",
    @SerializedName("plan") var plan: String="",
    @SerializedName("currentSeason") var currentSeason: CurrentSeason =CurrentSeason(),
    @SerializedName("numberOfAvailableSeasons") var numberOfAvailableSeasons: Int? = null,
    @SerializedName("lastUpdated") var lastUpdated: String? = null,
)


data class Winner (

    @SerializedName("id"          ) var id          : Int?    = null,
    @SerializedName("name"        ) var name        : String? = null,
    @SerializedName("shortName"   ) var shortName   : String? = null,
    @SerializedName("tla"         ) var tla         : String? = null,
    @SerializedName("crest"       ) var crest       : String? = null,
    @SerializedName("address"     ) var address     : String? = null,
    @SerializedName("website"     ) var website     : String? = null,
    @SerializedName("founded"     ) var founded     : Int?    = null,
    @SerializedName("clubColors"  ) var clubColors  : String? = null,
    @SerializedName("venue"       ) var venue       : String? = null,
    @SerializedName("lastUpdated" ) var lastUpdated : String? = null

)

