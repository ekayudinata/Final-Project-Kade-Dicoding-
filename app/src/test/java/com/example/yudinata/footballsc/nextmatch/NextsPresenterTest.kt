package com.example.yudinata.footballsc.nextmatch

import com.example.yudinata.footballsc.feature.matchs.nextmatch.NextsPresenter
import com.example.yudinata.footballsc.feature.matchs.nextmatch.NextsView
import com.example.yudinata.footballsc.model.EventItem
import com.example.yudinata.footballsc.model.EventResponse
import com.example.yudinata.footballsc.network.ApiRepository
import com.example.yudinata.footballsc.network.TheSportDBApi
import com.example.yudinata.footballsc.util.TestContextProvider
import com.google.gson.Gson
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NextsPresenterTest {

    @Mock
    private
    lateinit var view: NextsView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: NextsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextsPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testgetTeamList() {

        val teams: MutableList<EventItem> = mutableListOf()
        val response = EventResponse(teams)
        val league = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)),
                EventResponse::class.java
        )).thenReturn(response)

        presenter.getTeamList(league)

        verify(view).showLoading()
        verify(view).showTeamList(teams)
        verify(view).hideLoading()

    }
}