package com.example.yudinata.footballsc.feature.searchMatch

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.yudinata.footballsc.R
import com.example.yudinata.footballsc.model.SearchMatchItem
import com.example.yudinata.footballsc.util.SetDate
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class SearchMatchAdapter (val events: List<SearchMatchItem>,
                          val listener: (SearchMatchItem) -> Unit) : RecyclerView.Adapter<SearchMatchAdapter.LastMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMatchViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.single_match_layout, parent, false)
        return LastMatchViewHolder(v)

    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: LastMatchViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }




    class LastMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val date: TextView = view.find(R.id.tv_date)
        val teamHome: TextView = view.find(R.id.tv_home_team_name)
        val teamAway: TextView = view.find(R.id.tv_away_team_name)
        val scoreHome: TextView? = view.find(R.id.tv_home_score)
        val scoreAway: TextView? = view.find(R.id.tv_away_score)
        var cekNull1:String=""
        var cekNull2:String=""

        fun bindItem(event: SearchMatchItem, listener: (SearchMatchItem) -> Unit) {
            date.text = SetDate.getLongDate(event.dateEvent.toString())
            teamHome.text = event.strHomeTeam
            teamAway.text = event.strAwayTeam
            cekNull1=event.intHomeScore.toString()
            cekNull1=cekNull1.replace("null","")
            scoreHome?.text = cekNull1
            cekNull2=event.intAwayScore.toString()
            cekNull2=cekNull2.replace("null","")
            scoreAway?.text = cekNull2
            itemView.onClick { listener(event) }
        }
    }
}