package com.example.yudinata.footballsc.feature.detail

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import com.example.yudinata.footballsc.R.menu.detail_menu
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.bumptech.glide.Glide
import com.example.yudinata.footballsc.Database.Favorite
import com.example.yudinata.footballsc.Database.database
import com.example.yudinata.footballsc.R
import com.example.yudinata.footballsc.R.drawable.favorite_icon
import com.example.yudinata.footballsc.R.drawable.favorite_icon_yes
import com.example.yudinata.footballsc.R.id.*
import com.example.yudinata.footballsc.model.DetailItem
import com.example.yudinata.footballsc.model.Match
import com.example.yudinata.footballsc.util.SetDate
import com.example.yudinata.footballsc.util.SetTime
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout



class MatchDetailActivity : AppCompatActivity(), DetailView {
    lateinit var swipeRefresh:SwipeRefreshLayout
    lateinit var presenter: MatchDetailPresenter

    lateinit var imgHomeBadge: ImageView
    lateinit var imgAwayBadge: ImageView

    lateinit var date :TextView
    lateinit var timeEvent:TextView
    lateinit var homescore :TextView
    lateinit var awayscore :TextView
    lateinit var hometeam :TextView
    lateinit var awayteam :TextView
    lateinit var homegoal :TextView
    lateinit var awaygoal :TextView
    lateinit var homeshots :TextView
    lateinit var awayshot :TextView
    lateinit var homegoalKeeper :TextView
    lateinit var awaygoalKeeper :TextView
    lateinit var homedefense :TextView
    lateinit var awaydefense :TextView
    lateinit var homemidfield:TextView
    lateinit var awaymidfield :TextView
    lateinit var homeforward :TextView
    lateinit var awayforward :TextView
    lateinit var homesubst :TextView
    lateinit var awaysubst :TextView


    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var idEvent: String
    private var saveDateEvent: String =""
    private lateinit var saveTime:String

    private lateinit var saveHomeID: String
    private lateinit var saveHomeScore: String
    private lateinit var saveHomeTeam: String

    private lateinit var saveAwayID: String
    private lateinit var saveAwayScore: String
    private lateinit var saveAwayTeam: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         idEvent = intent.getStringExtra("idEvent")

        val idHomeTeam = intent.getStringExtra("idHomeTeam")
        val idAwayTeam = intent.getStringExtra("idAwayTeam")


        supportActionBar?.title="Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        //Main Layout
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            swipeRefresh = swipeRefreshLayout {
                id = swipe_refresh
                setColorSchemeResources(android.R.color.black,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                            scrollView {
                        linearLayout {
                            orientation = LinearLayout.VERTICAL
                            padding = dip(16)


                           date= textView {
                                gravity = Gravity.CENTER
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                            }

                            timeEvent= textView {
                                gravity = Gravity.CENTER
                                textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                            }


                            linearLayout {
                                padding = dip(16)
                                gravity = Gravity.CENTER

                                homescore=textView {
                                    textSize = 48f
                                }

                                textView {
                                    padding = dip(16)
                                    textSize = 24f
                                    text = "vs"
                                }

                               awayscore= textView {
                                    textSize = 48f
                                }
                            }


                            linearLayout {
                                linearLayout {
                                    orientation = LinearLayout.VERTICAL

                                    imgHomeBadge = imageView() {
                                    }.lparams {
                                        width = dip(100)
                                        height = dip(100)
                                        gravity = Gravity.CENTER
                                    }

                                   hometeam= textView {
                                        gravity = Gravity.CENTER
                                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                        textSize = 24f
                                        setTypeface(null, Typeface.BOLD)
                                    }

                                }.lparams(matchParent, wrapContent, 1f)

                                linearLayout {
                                    orientation = LinearLayout.VERTICAL

                                    imgAwayBadge = imageView() {
                                    }.lparams {
                                        width = dip(100)
                                        height = dip(100)
                                        gravity = Gravity.CENTER
                                    }

                                  awayteam= textView {
                                        gravity = Gravity.CENTER
                                        textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                        textSize = 24f
                                        setTypeface(null, Typeface.BOLD)
                                    }

                                }.lparams(matchParent, wrapContent, 1f)
                            }

                            view {
                                backgroundColor = Color.GREEN
                            }.lparams(matchParent, dip(1)) {
                                topMargin = dip(8)
                            }


                            linearLayout {
                                topPadding = dip(8)

                             homegoal=textView {
                                }.lparams(matchParent, wrapContent, 1f)

                                textView {
                                    leftPadding = dip(8)
                                    rightPadding = dip(8)
                                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                    text = "Goals"
                                }

                              awaygoal=  textView {
                                    gravity = Gravity.END
                                }.lparams(matchParent, wrapContent, 1f)
                            }


                            linearLayout {
                                topPadding = dip(16)

                              homeshots=  textView {
                                }.lparams(matchParent, wrapContent, 1f)

                                textView {
                                    leftPadding = dip(8)
                                    rightPadding = dip(8)
                                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                    text = "Shots"
                                }

                              awayshot=  textView {
                                    gravity = Gravity.END
                                }.lparams(matchParent, wrapContent, 1f)
                            }

                            view {
                                backgroundColor = Color.GREEN
                            }.lparams(matchParent, dip(1)) {
                                topMargin = dip(8)
                            }


                            textView {
                                topPadding = dip(8)
                                gravity = Gravity.CENTER
                                textSize = 18f
                                setTypeface(null, Typeface.BOLD)
                                text = "Lineups"
                            }


                            linearLayout {
                                topPadding = dip(16)

                             homegoalKeeper =   textView {
                                }.lparams(matchParent, wrapContent, 1f)

                                textView {
                                    leftPadding = dip(8)
                                    rightPadding = dip(8)
                                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                    text = "Goal Keeper"
                                }

                              awaygoalKeeper=  textView {
                                    gravity = Gravity.END
                                }.lparams(matchParent, wrapContent, 1f)
                            }


                            linearLayout {
                                topPadding = dip(16)

                             homedefense=   textView {
                                }.lparams(matchParent, wrapContent, 1f)

                                textView {
                                    leftPadding = dip(8)
                                    rightPadding = dip(8)
                                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                    text = "Defense"
                                }

                               awaydefense= textView {
                                    gravity = Gravity.END
                                }.lparams(matchParent, wrapContent, 1f)
                            }


                            linearLayout {
                                topPadding = dip(16)

                              homemidfield=   textView {
                                }.lparams(matchParent, wrapContent, 1f)

                                textView {
                                    leftPadding = dip(8)
                                    rightPadding = dip(8)
                                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                    text = "Midfield"
                                }

                               awaymidfield= textView {
                                    gravity = Gravity.END
                                }.lparams(matchParent, wrapContent, 1f)
                            }

                            linearLayout {
                                topPadding = dip(16)

                             homeforward=   textView {
                                }.lparams(matchParent, wrapContent, 1f)

                                textView {
                                    leftPadding = dip(8)
                                    rightPadding = dip(8)
                                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                    text = "Forward"
                                }

                              awayforward=  textView {
                                    gravity = Gravity.END
                                }.lparams(matchParent, wrapContent, 1f)
                            }

                            linearLayout {
                                topPadding = dip(16)

                             homesubst=   textView {
                                    id = home_substitutes
                                }.lparams(matchParent, wrapContent, 1f)

                                textView {
                                    leftPadding = dip(8)
                                    rightPadding = dip(8)
                                    textColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                                    text = "Substitutes"
                                }

                              awaysubst=  textView {
                                    gravity = Gravity.END
                                }.lparams(matchParent, wrapContent, 1f)
                            }
                        }
                    }
//                }
            }
        }

        favoriteState()
        presenter = MatchDetailPresenter(this)
        presenter.getTeamDetails(idHomeTeam.toString(),idAwayTeam.toString())
        presenter.getDetailEvent(idEvent)


        swipeRefresh.onRefresh {
            presenter.getTeamDetails(idHomeTeam.toString(),idAwayTeam.toString())
            presenter.getDetailEvent(idEvent)
             }
    } //end metod onCreate

    override fun showTeamDetails(dataHomeTeam: List<DetailItem>, dataAwayTeam: List<DetailItem>) {
        Glide.with(applicationContext).load(dataHomeTeam[0].strTeamBadge).into(imgHomeBadge)
        Glide.with(applicationContext).load(dataAwayTeam[0].strTeamBadge).into(imgAwayBadge)

    }



    override fun showMatchDetail(dataMatch:List<Match>){

        saveDateEvent=dataMatch[0].dateEvent.toString()
        saveTime=dataMatch[0].strTime.toString()

        saveHomeTeam = dataMatch[0].strHomeTeam.toString()
        saveHomeID = dataMatch[0].idHomeTeam.toString()
        saveHomeScore=dataMatch[0].intHomeScore.toString()

        saveAwayTeam = dataMatch[0].strAwayTeam.toString()
        saveAwayID = dataMatch[0].idAwayTeam.toString()
        saveAwayScore = dataMatch[0].intAwayScore.toString()

        swipeRefresh.isRefreshing = false


        date.text =SetDate.getLongDate(dataMatch[0].dateEvent.toString())
        timeEvent.text =SetTime.getTime(dataMatch[0].strTime.toString())
        //home
        homescore.text = dataMatch[0].intHomeScore
        hometeam.text = dataMatch[0].strHomeTeam
        homeshots.text = dataMatch[0].intHomeShots
        homegoal.text= dataMatch[0].strHomeGoalDetails
        homegoalKeeper.text = dataMatch[0].strHomeLineupGoalkeeper
        homedefense.text= dataMatch[0].strHomeLineupDefense
        homeforward.text= dataMatch[0].strHomeLineupForward
        homemidfield.text= dataMatch[0].strHomeLineupMidfield
        homesubst.text=dataMatch[0].strHomeLineupSubstitutes


        //away
        awayscore.text = dataMatch[0].intAwayScore
        awayteam.text = dataMatch[0].strAwayTeam
        awayshot.text = dataMatch[0].intAwayShots
        awaygoal.text= dataMatch[0].strAwayGoalDetails
        awaygoalKeeper.text = dataMatch[0].strAwayLineupGoalkeeper
        awaydefense.text= dataMatch[0].strAwayLineupDefense
        awayforward.text= dataMatch[0].strAwayLineupForward
        awaymidfield.text= dataMatch[0].strAwayLineupMidfield
        awaysubst.text=dataMatch[0].strAwayLineupSubstitutes
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to idEvent)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu,menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            return when (item.itemId){
                android.R.id.home ->{
                    finish()
                    true
                }
                add_to_favorite ->{
                    if (isFavorite)
                        removeFavorite()
//                        Toast.makeText(this,"Hapus Favorite Belu Berfungsi ", Toast.LENGTH_SHORT).show()
                    else
                        addToFavorite()
                    isFavorite = !isFavorite
                    setFavorite()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
        return true
    }


    private fun addToFavorite(){
        if(saveDateEvent == ""){
            return snackbar(swipeRefresh, "You are offline").show()
        }
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to idEvent,
                        Favorite.DATE_EVENT to saveDateEvent,
                        Favorite.TIME_EVENT to saveTime,

                        Favorite.HOME_ID to saveHomeID,
                        Favorite.HOME_TEAM to saveHomeTeam,
                        Favorite.HOME_SCORE to saveHomeScore,
                        //Away
                        Favorite.AWAY_ID to saveAwayID,
                        Favorite.AWAY_TEAM to saveAwayTeam,
                        Favorite.AWAY_SCORE to saveAwayScore
                        )
            }
            snackbar(swipeRefresh, "Add to favorite").show()
        }
        catch (e:SQLiteConstraintException){
            snackbar(swipeRefresh, "Error : ${e.message}").show()
//            Toast.makeText(this, "Error: ${e.message}",Toast.LENGTH_SHORT).show()
        }
    } //end fun addFavorite


    private fun removeFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                        "id" to idEvent)
            }
            snackbar(swipeRefresh, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun setFavorite(){
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, favorite_icon_yes)
        else
            menuItem?.getItem(0)?.icon= ContextCompat.getDrawable(this, favorite_icon)
    }
}









