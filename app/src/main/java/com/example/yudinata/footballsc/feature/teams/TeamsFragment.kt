package com.example.yudinata.footballsc.feature.teams

import android.R
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.yudinata.footballsc.feature.teamsDetail.TeamDetailActivity
import com.example.yudinata.footballsc.model.TeamsItem
import com.example.yudinata.footballsc.network.ApiRepository
import com.example.yudinata.footballsc.util.invisible
import com.example.yudinata.footballsc.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import com.example.yudinata.footballsc.R.string.app_name
import com.example.yudinata.footballsc.R.attr.actionBarSize
import com.example.yudinata.footballsc.R.id.search_team
import com.example.yudinata.footballsc.R.id.toolBars
import com.example.yudinata.footballsc.feature.searchTeam.SearchTeamActivity
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity

class TeamsFragment: Fragment(),AnkoComponent<Context> ,TeamsView{
    private  var teams:MutableList<TeamsItem> = mutableListOf()

    private lateinit var swipeRefresh:SwipeRefreshLayout
    private lateinit var listTeam:RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var adapter : TeamsAdapter
    private lateinit var presenter: TeamsPresenter
    private val idleague = arrayOf("4328","4329","4331","4332","4334","4335" )
    private lateinit var idItem:String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val searchImage = view?.find<ImageView>(search_team)
        searchImage?.onClick {
            startActivity<SearchTeamActivity>()
        }


        adapter = TeamsAdapter(teams,ctx) {
            ctx.startActivity<TeamDetailActivity>(
                    "idTeam" to "${it.idTeam}",
                    "nameTeam" to "${it.strTeam}",
                    "badgeTeam" to "${it.strTeamBadge}",
                    "formedYear" to "${it.intFormedYear}",
                    "stadium" to "${it.strStadium}"
            )
        }

        val spinnerItems = resources.getStringArray(com.example.yudinata.footballsc.R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx,R.layout.simple_spinner_dropdown_item, spinnerItems)

        spinner.adapter = spinnerAdapter

        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                idItem = idleague[position]
//                Log.d("isi position : ",idleague[position])
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

    override fun createView(ui: AnkoContext<Context>): View =with(ui){
    coordinatorLayout {
        lparams(matchParent, matchParent)
        appBarLayout {
            toolbar {
                id = toolBars
                linearLayout {
                    lparams(width= matchParent,height = wrapContent)
                    orientation = LinearLayout.HORIZONTAL
                    textView {
                        textResource = app_name
                        textSize=20f
                        setTypeface( null,Typeface.BOLD)
                        textColor = ContextCompat.getColor(ctx, com.example.yudinata.footballsc.R.color.white)
                    }.lparams(height= wrapContent,width = dip(0),weight = 3F)

                    imageView {
                        id = search_team
                        setImageResource(R.drawable.ic_menu_search)

                    }.lparams(height= wrapContent,width = dip(0),weight = 1F){
                        gravity=Gravity.END
                    }
                }

            }.lparams(width = matchParent, height = dimenAttr(actionBarSize),weight = 1F)
        }.lparams(width= matchParent,height = wrapContent){
            elevation = 0F
        }



        linearLayout {

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

                    listTeam = recyclerView {
                        id = com.example.yudinata.footballsc.R.id.listEventt
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }.lparams (width = matchParent, height = wrapContent){
            behavior= AppBarLayout.ScrollingViewBehavior()
        }

    }

    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<TeamsItem>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

}


