package com.example.yudinata.footballsc.feature.teamsDetail.players

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
import com.example.yudinata.footballsc.R
import com.example.yudinata.footballsc.model.PlayerItem
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class PlayerAdapter (private val players: List<PlayerItem>, private val listener: (PlayerItem) -> Unit)
    : RecyclerView.Adapter<NextViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextViewHolder {
        return NextViewHolder(NextUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: NextViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

    override fun getItemCount(): Int = players.size

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
                        id= R.id.player_badge
                    }.lparams(width=dip(50),height = dip(50))

                    textView {
                        id = R.id.player_name
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    }.lparams(matchParent, wrapContent,1f){
                        setMargins(dip(6),dip(0),dip(0),dip(0))
                    }

                    textView {
                        id = R.id.player_position
                        gravity=Gravity.CENTER
                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                    }.lparams(matchParent, wrapContent,1f)

                }.lparams(matchParent, matchParent) {
                    setMargins(dip(6), dip(6), dip(6), dip(6))
                }
            }
        }
    }

}

class NextViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val playerBadge: ImageView = view.find(R.id.player_badge)
    private val playerName: TextView = view.find(R.id.player_name)
    private val playerPosition: TextView =view.find(R.id.player_position)

    fun bindItem(player: PlayerItem, listener: (PlayerItem) -> Unit) {
        Glide.with(itemView.context).load(player.strCutout).into(playerBadge)
        playerName.text = player.strPlayer
        playerPosition.text= player.strPosition
        itemView.onClick { listener(player) }
    }
}