package com.example.yudinata.footballsc.feature.teamsDetail

import android.annotation.TargetApi
import android.database.sqlite.SQLiteConstraintException
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.yudinata.footballsc.Database.Favorite
import com.example.yudinata.footballsc.Database.TeamsFav
import com.example.yudinata.footballsc.Database.database
import com.example.yudinata.footballsc.R
import com.example.yudinata.footballsc.R.drawable.favorite_icon
import com.example.yudinata.footballsc.R.drawable.favorite_icon_yes
import com.example.yudinata.footballsc.R.id.add_to_favorite
import com.example.yudinata.footballsc.R.menu.detail_menu
import com.example.yudinata.footballsc.ViewPagerAdapter
import com.example.yudinata.footballsc.feature.teamsDetail.overView.OverViewFragment
import com.example.yudinata.footballsc.feature.teamsDetail.players.PlayerFragment
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar


class TeamDetailActivity : AppCompatActivity() {

    private lateinit var idTeam:String
    private lateinit var nameTeam:String
    private lateinit var badgeTeam:String
    private lateinit var stadium:String
    private lateinit var formedYear:String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        idTeam = intent.getStringExtra("idTeam")
        nameTeam = intent.getStringExtra("nameTeam")
        badgeTeam = intent.getStringExtra("badgeTeam")
        formedYear = intent.getStringExtra("formedYear")
        stadium = intent.getStringExtra("stadium")


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = null


        name_team.text = nameTeam
        formed_year.text = formedYear
        stadium_.text = stadium
        Glide.with(applicationContext).load(badgeTeam).into(image_Team)

        val bundle=Bundle()

        val adapter = ViewPagerAdapter(supportFragmentManager)
        val overViewFragment = OverViewFragment()
        val playersFragment = PlayerFragment()
        overViewFragment.arguments = bundle
        playersFragment.arguments = bundle
        adapter.populateFragment(overViewFragment, "Overview")
        adapter.populateFragment(playersFragment, "Players")
        viewpagerTeam.adapter = adapter
        tabs.setupWithViewPager(viewpagerTeam)

        favoriteState()

    }

     fun getId(): String {
         return idTeam
     }


    private fun favoriteState(){
        database.use {
            val result = select(TeamsFav.TABLE_TEAM)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to idTeam)
            val favorite = result.parseList(classParser<TeamsFav>())
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
        try {
            database.use {
                insert(TeamsFav.TABLE_TEAM,
                        TeamsFav.TEAM_ID to idTeam,
                        TeamsFav.TEAM_NAME to nameTeam,
                        TeamsFav.TEAM_BADGE to badgeTeam,
                        TeamsFav.TEAM_FORMED to formedYear,
                        TeamsFav.TEAM_STADIUM to stadium
                )
            }
            Toast.makeText(applicationContext,"Data Berhasil Ditambahkan",Toast.LENGTH_SHORT).show()
//            snackbar(swipeRefresh, "Add to favorite").show()
        }
        catch (e: SQLiteConstraintException){
            Toast.makeText(applicationContext, "Error: ${e.message}",Toast.LENGTH_SHORT).show()
        }
    } //end fun addFavorite


    private fun removeFavorite(){
        try {
            database.use {
                delete(TeamsFav.TABLE_TEAM, "(TEAM_ID = {id})",
                        "id" to idTeam )
            }
            Toast.makeText(applicationContext, "Removed to favorite",Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(applicationContext, "Error: ${e.message}",Toast.LENGTH_SHORT).show()
        }
    }



    private fun setFavorite(){
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, favorite_icon_yes)
        else
            menuItem?.getItem(0)?.icon= ContextCompat.getDrawable(this, favorite_icon)
    }

}
