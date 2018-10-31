package com.example.yudinata.footballsc.feature.matchs.nextmatch

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.provider.CalendarContract
import android.provider.CalendarContract.Events.*
import android.support.annotation.RequiresApi
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
import com.example.yudinata.footballsc.R.string.vs
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat


class NextsAdapter(private val nexts: List<EventItem>, private val listener: (EventItem) -> Unit)
    : RecyclerView.Adapter<NextViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextViewHolder {
        return NextViewHolder(NextUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: NextViewHolder, position: Int) {
        holder.bindItem(nexts[position], listener)
    }

    override fun getItemCount(): Int = nexts.size

}

class NextUI : AnkoComponent<ViewGroup> {


    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout {
                lparams(matchParent, wrapContent)
                orientation = LinearLayout.VERTICAL

                linearLayout {
                    backgroundColor = Color.WHITE
                    orientation = LinearLayout.VERTICAL
                    padding = dip(8)


                    textView {
                        id = R.id.dateM
                        visibility = View.GONE
                    }

                    textView {
                        id = R.id.timeM
                        visibility = View.GONE
                    }

                    imageButton {
                        id = R.id.btnImage
                        backgroundColor =Color.WHITE
                        setImageResource(R.drawable.alarm_icon)
                        onClick {
//                            ctx.startActivity<simpleActivity>()

                            val homeTeam: TextView = view.find(team_home2)
                            val awayTeam: TextView = view.find(team_away2)

                            val dateMa: TextView = view.find(dateM)
                            val time: TextView = view.find(timeM)

                            val clock = time.text.toString()
                            val dte =  dateMa.text.toString()
//
                            val dateWithClock = "$dte $clock"
                            val simpleDate = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                            val date = simpleDate.parse(dateWithClock)

                            val timeInMillis = date.time

                            val intent = Intent(Intent.ACTION_INSERT)
                                    .setData(CONTENT_URI)
                                    .putExtra(TITLE, homeTeam.text.toString() + " vs " + awayTeam.text.toString())
                                    .putExtra(EVENT_LOCATION, "Television")
                                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, timeInMillis)
                                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, timeInMillis+5400000)


                            ctx.startActivity(intent)
                        }

                    }.lparams(wrapContent, wrapContent)

                    textView {
                        id = date_match
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
                            id = team_home2
                            gravity = Gravity.CENTER
                            textSize = 18f
                        }.lparams(matchParent, wrapContent, 1f)


                            textView {
                                textResource = vs
                                setTypeface(null, Typeface.BOLD)
                            }.lparams(wrapContent, wrapContent){
                                setMargins(dip(2),dip(0),dip(2),dip(0))
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


class NextViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val teamHome: TextView = view.find(team_home2)
    private val teamAway: TextView = view.find(team_away2)
    private val dateMatch:TextView =view.find(date_match)
    private val timeMatch:TextView = view.find(idTime)
    private val sendDate:TextView = view.find(dateM)
    private val sendTime:TextView = view.find(timeM)

    fun bindItem(next: EventItem, listener: (EventItem) -> Unit) {
        teamHome.text = next.strHomeTeam
        teamAway.text = next.strAwayTeam
        timeMatch.text = SetTime.getTime(next.strTime.toString())
        sendDate.text = next.dateEvent
        sendTime.text = next.strTime
        dateMatch.text = SetDate.getLongDate(next.dateEvent.toString())
        itemView.onClick { listener(next) }
    }


}