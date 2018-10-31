package com.example.yudinata.footballsc.prevmatch

import com.example.yudinata.footballsc.feature.matchs.prevmatch.PrevsPresenter
import com.example.yudinata.footballsc.feature.matchs.prevmatch.PrevsView
import com.example.yudinata.footballsc.model.EventItem
import com.example.yudinata.footballsc.model.EventResponse
import com.example.yudinata.footballsc.network.ApiRepository
import com.example.yudinata.footballsc.network.TheSportDBApi
import com.example.yudinata.footballsc.util.TestContextProvider
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PrevsPresenterTest {

    @Mock
    private
    lateinit var view: PrevsView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var presenter: PrevsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PrevsPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testgetTeamList() {
        val teams: MutableList<EventItem> = mutableListOf()
        val response = EventResponse(teams)
        val league = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getPrevs(league)),
                EventResponse::class.java
        )).thenReturn(response)

        presenter.getTeamList(league)

        verify(view).showLoading()
        verify(view).showTeamList(teams)
        verify(view).hideLoading()
    }
}