package com.example.pickapp.dto

import com.google.gson.annotations.SerializedName

data class Reply (

    @SerializedName("total"     ) var total     : Int?            = null,
    @SerializedName("totalHits" ) var totalHits : Int?            = null,
    @SerializedName("hits"      ) var hits      : ArrayList<Hits> = arrayListOf()

)