package com.example.yudinata.footballsc.feature.favorite.favoriteMatch

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.yudinata.footballsc.Database.Favorite
import com.example.yudinata.footballsc.R
import com.example.yudinata.footballsc.util.SetDate
import com.example.yudinata.footballsc.util.SetTime
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavMatchAdapter (private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit): RecyclerView.Adapter<FavoriteViewHolder>() {

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
                    orientation = LinearLayout.VERTICAL
                    padding = dip(8)

                    textView {
                        id = R.id.date_match
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        gravity = Gravity.CENTER
                    }.lparams(matchParent, wrapContent)

                    textView {
                        id = R.id.idTime
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        gravity = Gravity.CENTER
                    }.lparams(matchParent, wrapContent)

                    linearLayout {
                        gravity = Gravity.CENTER_VERTICAL

                        textView {
                            id = R.id.team_home2
                            gravity = Gravity.CENTER
                            textSize = 18f
                        }.lparams(matchParent, wrapContent, 1f)

                        linearLayout {
                            gravity = Gravity.CENTER_VERTICAL

                            textView {
                                id = R.id.home_score
                                padding = dip(8)
                                textSize = 20f
                                setTypeface(null, Typeface.BOLD)
                            }

                            textView {
                                text = "vs"
                                setTypeface(null, Typeface.BOLD)
                            }

                            textView {
                                id= R.id.away_score
                                padding = dip(8)
                                textSize = 20f
                                setTypeface(null, Typeface.BOLD)
                            }
                        }

                        textView {
                            id = R.id.team_away2
                            gravity = Gravity.CENTER
                            textSize = 18f
                        }.lparams(matchParent, wrapContent, 1f)
                    }
                }.lparams(matchParent, matchParent) {
                    setMargins(dip(6), dip(6), dip(6), dip(6))
                }
            }
        }
    }

}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val teamHome: TextView = view.find(R.id.team_home2)
    private val scoreHome: TextView = view.find(R.id.home_score)
    private val teamAway: TextView = view.find(R.id.team_away2)
    private val scoreAway: TextView = view.find(R.id.away_score)
    private val dateMatch: TextView = view.find(R.id.date_match)
    private val timeMatch: TextView = view.find(R.id.idTime)


    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        var score1:String
        var score2:String
        teamHome.text = favorite.strHomeTeam
        score1 = favorite.intHomeScore!!
        score1 = score1.replace("null","")
        scoreHome.text = score1
        teamAway.text = favorite.strAwayTeam
        score2= favorite.intAwayScore!!
        score2 = score2.replace("null","")
        scoreAway.text = score2
        dateMatch.text = SetDate.getLongDate(favorite.dateEvent.toString())

        timeMatch.text = SetTime.getTime(favorite.strTime.toString())
        itemView.onClick { listener(favorite) }
    }
}