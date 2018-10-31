package com.example.yudinata.footballsc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.yudinata.footballsc.R.id.*
import com.example.yudinata.footballsc.feature.favorite.FavoriteFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.example.yudinata.footballsc.feature.teams.TeamsFragment
import com.example.yudinata.footballsc.util.SetTime

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                prevss -> {
                    loadPrevsFragment(savedInstanceState)
                }
                nextss -> {
                    loadNextsFragment(savedInstanceState)
                }
                favorites ->{
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = prevss

    }



    private fun loadPrevsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchTabFragment(), MatchTabFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun  loadNextsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun   loadFavoritesFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                    .commit()
        }
    }


}
