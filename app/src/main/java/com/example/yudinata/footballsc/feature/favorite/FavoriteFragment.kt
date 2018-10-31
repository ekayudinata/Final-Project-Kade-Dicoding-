package com.example.yudinata.footballsc.feature.favorite

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yudinata.footballsc.R
import com.example.yudinata.footballsc.ViewPagerAdapter
import com.example.yudinata.footballsc.feature.favorite.favoriteMatch.FavMatchFragment
import com.example.yudinata.footballsc.feature.favorite.favoriteTeams.FavTeamsFragment

class FavoriteFragment:Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val viewPager = view.findViewById<ViewPager>(R.id.container)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)
        val adapter = ViewPagerAdapter(childFragmentManager)
        setHasOptionsMenu(true)
        adapter.populateFragment(FavTeamsFragment(), "Teams")
        adapter.populateFragment(FavMatchFragment(), "Matchs")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}