package com.example.yudinata.footballsc.feature.teams


import android.content.Context
import android.graphics.Color
import com.example.yudinata.footballsc.R.id.team_name
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.yudinata.footballsc.R
import com.example.yudinata.footballsc.R.id.team_badge
import com.example.yudinata.footballsc.model.TeamsItem
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class TeamsAdapter(private val teams: List<TeamsItem>, context: Context, private val listener: (TeamsItem) -> Unit)
    : RecyclerView.Adapter<TeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        return TeamsViewHolder(NextUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.size

}

class NextUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){


            linearLayout {
                lparams(matchParent, wrapContent)
                orientation = LinearLayout.VERTICAL

                linearLayout {
                    backgroundColor = Color.WHITE
                    orientation = LinearLayout.HORIZONTAL
                    padding = dip(8)

                    imageView {
                        id = team_badge
                    }.lparams(width = dip(50),height = dip(50))

                    textView {
                        id = team_name
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        gravity = Gravity.CENTER
                    }.lparams(matchParent, wrapContent)

                }.lparams(matchParent, matchParent) {
                    setMargins(dip(6), dip(6), dip(6), dip(6))
                }
            }


        }
    }

}

class TeamsViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val teamName: TextView = view.find(team_name)
    private val teamBadge: ImageView = view.find(team_badge)

    fun bindItem(team: TeamsItem, listener: (TeamsItem) -> Unit) {
        teamName.text = team.strTeam
        Glide.with(itemView.context).load(team.strTeamBadge).into(teamBadge)
        itemView.onClick { listener(team) }
    }
}