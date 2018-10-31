package com.example.yudinata.footballsc.feature.matchs.nextmatch

import android.R
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.yudinata.footballsc.feature.detail.MatchDetailActivity
import com.example.yudinata.footballsc.network.ApiRepository
import com.example.yudinata.footballsc.R.id.listEventt
import com.example.yudinata.footballsc.R.array.league
import com.example.yudinata.footballsc.model.EventItem
import com.example.yudinata.footballsc.util.invisible
import com.example.yudinata.footballsc.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class NextsFragment : Fragment(), AnkoComponent<Context>, NextsView {

    private var teams: MutableList<EventItem> = mutableListOf()
    private lateinit var presenter: NextsPresenter
    private lateinit var adapter: NextsAdapter
    private lateinit var listEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private val idleague = arrayOf("4328","4329","4331","4332","4334","4335" )
    private lateinit var idItem:String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = NextsAdapter(teams) {
            ctx.startActivity<MatchDetailActivity>(
                    "idEvent" to "${it.idEvent}",
                    "idHomeTeam" to "${it.idHomeTeam}",
                    "idAwayTeam" to "${it.idAwayTeam}"
            )

        }

        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(ctx,R.layout.simple_spinner_dropdown_item, spinnerItems)

        spinner.adapter = spinnerAdapter

        listEvent.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = NextsPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                idItem = idleague[position]
                Log.d("isi position : ",idleague[position])
                presenter.getTeamList(idItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }



        swipeRefresh.onRefresh {
            presenter.getTeamList(idItem)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner()

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.black,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    listEvent = recyclerView {
                        id =listEventt
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<EventItem>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

}


