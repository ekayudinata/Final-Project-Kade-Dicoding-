package com.example.yudinata.footballsc.feature.searchTeam

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.yudinata.footballsc.R
import com.example.yudinata.footballsc.model.TeamsItem
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class SearchTeamAdapter (val teams: List<TeamsItem>,
                         val listener: (TeamsItem) -> Unit): RecyclerView.Adapter<TeamViewHoldera>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHoldera {
        return TeamViewHoldera(TeamUIa().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamViewHoldera, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.size
}

class TeamUIa : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.team_badge
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.team_name
                    textSize = 18f
                    gravity= Gravity.CENTER
                }.lparams{
                    margin = dip(15)
                }

            }
        }
    }

}

class TeamViewHoldera(view: View) : RecyclerView.ViewHolder(view){

    private val teamBadge: ImageView = view.find(R.id.team_badge)
    private val teamName: TextView = view.find(R.id.team_name)

    fun bindItem(teams: TeamsItem, listener: (TeamsItem) -> Unit) {
        Glide.with(itemView.context).load(teams.strTeamBadge).into(teamBadge)
        teamName.text = teams.strTeam
        itemView.onClick { listener(teams) }
    }
}