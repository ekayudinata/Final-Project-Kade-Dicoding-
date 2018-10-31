package com.example.yudinata.footballsc.model

import com.google.gson.annotations.SerializedName

data class PlayerResponse(

        @field:SerializedName("player")
        val player: List<PlayerItem>
)