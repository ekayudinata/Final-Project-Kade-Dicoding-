package com.example.yudinata.footballsc.feature.favorite.favoriteTeams


import android.graphics.Color

import android.support.v4.content.ContextCompat

import android.support.v7.widget.RecyclerView

import android.view.Gravity

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.yudinata.footballsc.Database.TeamsFav
import com.example.yudinata.footballsc.R

import org.jetbrains.anko.*

import org.jetbrains.anko.sdk25.coroutines.onClick


class FavTeamsAdapter (private val favorite: List<TeamsFav>, private val listener: (TeamsFav) -> Unit): RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }


    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    override fun getItemCount(): Int = favorite.size
}


class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {

            linearLayout {
                lparams(matchParent, wrapContent)
                orientation = LinearLayout.VERTICAL

                linearLayout {
                    backgroundColor = Color.WHITE
                    orientation = LinearLayout.HORIZONTAL
                    padding = dip(8)

                    imageView {
                        id = R.id.team_badge
                    }.lparams(width = dip(50),height = dip(50))

                    textView {
                        id = R.id.team_name
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

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val imageTeam: ImageView = view.find(R.id.team_badge)
    private val nameTeam: TextView = view.find(R.id.team_name)

    fun bindItem(favorite: TeamsFav, listener: (TeamsFav) -> Unit) {
        Glide.with(itemView.context).load(favorite.strTeamBadge).into(imageTeam)
        nameTeam.text=favorite.strTeam
        itemView.onClick { listener(favorite) }
    }
}