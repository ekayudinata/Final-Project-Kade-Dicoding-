package com.example.yudinata.footballsc.feature.searchMatch

import com.example.yudinata.footballsc.model.SearchMatchItem

interface SearchMatchView {
    fun showEventList(data:List<SearchMatchItem>)
}