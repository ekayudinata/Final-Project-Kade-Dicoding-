package com.example.yudinata.footballsc.feature.playerDetail

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.yudinata.footballsc.R
import com.example.yudinata.footballsc.model.PlayersItem
import org.jetbrains.anko.*

class PlayerDetailActivity : AppCompatActivity(),PlayerDetailView{

    private lateinit var presenter: PlayerDetailPresenter
    private lateinit var playerHeight:TextView
    private lateinit var playerWeight:TextView
    private lateinit var playerPosition:TextView
    private lateinit var playerDescrip:TextView
    private lateinit var playerBanner :ImageView
    private lateinit var idPlayer:String
    private lateinit var namePlayer:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idPlayer = intent.getStringExtra("idPlayer")

        scrollView {
        linearLayout{
            lparams(matchParent, matchParent)
            orientation = LinearLayout.VERTICAL

          playerBanner= imageView {
                id=R.id.player_badge
            }.lparams(matchParent, wrapContent)

            //
            linearLayout {
                orientation= LinearLayout.HORIZONTAL

                textView {
                    text = "Weight (kg)"
                    gravity = Gravity.CENTER

                }.lparams(height = wrapContent, width = dip(0),weight =1F)

                textView {
                    text = "Height (m)"
                    gravity = Gravity.CENTER

                }.lparams(height = wrapContent, width = dip(0),weight = 1F)

            }.lparams(width = matchParent, height = wrapContent){
                setMargins(dip(0),dip(0),dip(0),dip(8))
            }

            //data
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation= LinearLayout.HORIZONTAL

              playerWeight=  textView {
                    id = R.id.player_weight
                  textSize=13f
                  setTypeface( null, Typeface.BOLD)
                    gravity = Gravity.CENTER

                }.lparams(height = wrapContent, width = dip(0),weight =1F)

              playerHeight=textView {
                    id = R.id.player_height
                    textSize=13f
                    setTypeface( null, Typeface.BOLD)
                    gravity = Gravity.CENTER

                }.lparams(height = wrapContent, width = dip(0),weight = 1F)

            }

          playerPosition= textView {
                id =R.id.player_position
              gravity =Gravity.CENTER
            }

            view {
                backgroundColor = Color.BLACK
            }.lparams(matchParent, dip(1)) {
                topMargin = dip(8)
            }

           playerDescrip=textView {
                id = R.id.player_description
            }.lparams(wrapContent, wrapContent){
               setMargins(dip(6),dip(6),dip(6),dip(6))
           }

        }

        }//end anko layout






        //ngambil data dari presenter
        presenter = PlayerDetailPresenter(this)
        presenter.getPlayerDetails(idPlayer)

    }

    override fun showPlayerDetail(Data:List<PlayersItem>) {
        playerHeight.text = Data[0].strHeight
        playerWeight.text = Data[0].strWeight
        playerPosition.text = Data[0].strPosition
        playerDescrip.text = Data[0].strDescriptionEN
        namePlayer = Data[0].strPlayer.toString()
        Glide.with(applicationContext).load(Data[0].strThumb).into(playerBanner)

        supportActionBar?.title=namePlayer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

}
