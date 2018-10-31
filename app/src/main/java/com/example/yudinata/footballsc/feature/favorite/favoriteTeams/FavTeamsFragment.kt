package com.example.yudinata.footballsc.feature.favorite.favoriteTeams

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.yudinata.footballsc.Database.TeamsFav
import com.example.yudinata.footballsc.Database.database
import com.example.yudinata.footballsc.R
import com.example.yudinata.footballsc.feature.teamsDetail.TeamDetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavTeamsFragment: Fragment(), AnkoComponent<Context> {

    private var favorites: MutableList<TeamsFav> = mutableListOf()
    private lateinit var adapter: FavTeamsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavTeamsAdapter(favorites) {
            ctx.startActivity<TeamDetailActivity>(
                    "idTeam" to "${it.idTeam}",
                    "nameTeam" to "${it.strTeam}",
                    "badgeTeam" to "${it.strTeamBadge}",
                    "formedYear" to "${it.intFormedYear}",
                    "stadium" to "${it.strStadium}"
            )
        }

        recyclerView.adapter = adapter
        showFavorite()

        swipeRefresh.onRefresh {
            favorites.clear()
            showFavorite()
            //do reFresh
        }
    }

    fun showFavorite(){
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(TeamsFav.TABLE_TEAM)

            val Team = result.parseList(classParser<TeamsFav>())
            favorites.addAll(Team)
            adapter.notifyDataSetChanged()
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

            swipeRefresh = swipeRefreshLayout {
                id = R.id.swipe_refresh
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    recyclerView = recyclerView {
                        id = R.id.listEventt
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                }//end relativeLayout

            }
        }
    }//end create UI
}//end class