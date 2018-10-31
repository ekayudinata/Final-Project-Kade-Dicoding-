package com.example.yudinata.footballsc.feature.matchs.prevmatch

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.yudinata.footballsc.R
import com.example.yudinata.footballsc.R.id.*
import com.example.yudinata.footballsc.model.EventItem
import com.example.yudinata.footballsc.util.SetDate
import com.example.yudinata.footballsc.util.SetTime
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class PrevsAdapter(private val prevs: List<EventItem>, private val listener: (EventItem) -> Unit)
    : RecyclerView.Adapter<PrevViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevViewHolder {
        return PrevViewHolder(PrevUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: PrevViewHolder, position: Int) {
        holder.bindItem(prevs[position], listener)
    }

    override fun getItemCount(): Int = prevs.size

}

class PrevUI : AnkoComponent<ViewGroup> {
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
                        id =date_match
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        gravity = Gravity.CENTER
                    }.lparams(matchParent, wrapContent)

                    textView {
                        id = idTime
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                        gravity = Gravity.CENTER
                    }.lparams(matchParent, wrapContent)

                    linearLayout {
                        gravity = Gravity.CENTER_VERTICAL

                        textView {
                            id = team_home2
                            gravity = Gravity.CENTER
                            textSize = 18f
                        }.lparams(matchParent, wrapContent, 1f)

                        linearLayout {
                            gravity = Gravity.CENTER_VERTICAL

                            textView {
                                id = home_score
                                padding = dip(8)
                                textSize = 20f
                                setTypeface(null, Typeface.BOLD)
                            }

                            textView {
                                textResource= R.string.vs
                                setTypeface(null, Typeface.BOLD)
                            }

                            textView {
                                id= away_score
                                padding = dip(8)
                                textSize = 20f
                                setTypeface(null, Typeface.BOLD)
                            }
                        }

                        textView {
                            id = team_away2
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

class PrevViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val teamHome: TextView = view.find(team_home2)
    private val scoreHome: TextView = view.find(home_score)
    private val teamAway: TextView = view.find(team_away2)
    private val scoreAway: TextView = view.find(away_score)
    private val dateMatch:TextView = view.find(date_match)
    private val timeMatch:TextView = view.find(idTime)

    fun bindItem(prevs: EventItem, listener: (EventItem) -> Unit) {
        teamHome.text = prevs.strHomeTeam
        scoreHome.text = prevs.intHomeScore
        teamAway.text = prevs.strAwayTeam
        scoreAway.text = prevs.intAwayScore
        dateMatch.text = SetDate.getLongDate(prevs.dateEvent!!)
        timeMatch.text = SetTime.getTime(prevs.strTime.toString())

        itemView.onClick { listener(prevs) }
    }
}