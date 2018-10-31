package com.example.yudinata.footballsc

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*
import android.widget.ImageView
import com.example.yudinata.footballsc.feature.matchs.nextmatch.NextsFragment
import com.example.yudinata.footballsc.feature.matchs.prevmatch.PrevsFragment
import com.example.yudinata.footballsc.feature.searchMatch.SearchMatchactivity
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity


class MatchTabFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.match_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val imageView = view.findViewById<ImageView>(R.id.iv_search)
        val viewPager = view.findViewById<ViewPager>(R.id.container)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)
        val adapter = ViewPagerAdapter(childFragmentManager)
        setHasOptionsMenu(true)
        adapter.populateFragment(NextsFragment(), "Next")
        adapter.populateFragment(PrevsFragment(), "Past")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        imageView.onClick {
            startActivity<SearchMatchactivity>()
        }
    }

}
