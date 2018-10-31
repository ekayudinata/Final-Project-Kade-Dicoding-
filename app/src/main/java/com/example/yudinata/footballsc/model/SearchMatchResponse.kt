package com.example.yudinata.footballsc.model

import com.google.gson.annotations.SerializedName

data class SearchMatchResponse (
        @field:SerializedName("event")
        val event: List<SearchMatchItem>
)