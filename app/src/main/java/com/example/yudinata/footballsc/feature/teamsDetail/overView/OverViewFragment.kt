package com.example.yudinata.footballsc.feature.teamsDetail.overView

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.yudinata.footballsc.R.id.IdoverView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.example.yudinata.footballsc.R
import com.example.yudinata.footballsc.R.id.over_view
import com.example.yudinata.footballsc.feature.teamsDetail.TeamDetailActivity
import com.example.yudinata.footballsc.model.TeamsItem
import com.example.yudinata.footballsc.network.ApiRepository
import com.example.yudinata.footballsc.util.invisible
import com.example.yudinata.footballsc.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.*

class OverViewFragment:Fragment(),OverView{

    private var teams :MutableList<TeamsItem> = mutableListOf()
    private lateinit var idTeam:String
    private lateinit var swipeRefresh:SwipeRefreshLayout
    private lateinit var descripsi :TextView
    private lateinit var presenter: OverViewPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //get data fromactivity
        val activity= activity as TeamDetailActivity?
        if (activity != null) {
            idTeam = activity.getId()
        }

        Log.d("getId",idTeam)
        Log.d("isiteams",teams.toString())

        descripsi= view!!.find(over_view)


        val request = ApiRepository()
        val gson = Gson()
        presenter = OverViewPresenter(this,request,gson)
        presenter.getLookupTeam(idTeam)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  UI {
            scrollView {
                lparams(matchParent, matchParent)
            linearLayout {
                    textView {
                        id=R.id.over_view
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        text = "OverView"
                    }.lparams(matchParent, matchParent){
                        setMargins(dip(6),dip(6),dip(6),dip(6))
                    }
                }.lparams(matchParent, matchParent){
//                    marginEnd=dip(6)
                    setMargins(dip(6),dip(0),dip(0),dip(0))
                }
            }
        }.view


    }

override fun showOverView(data: List<TeamsItem>) {
        teams.clear()
        teams.addAll(data)
        descripsi.text = teams[0].strDescriptionEN
    }
}