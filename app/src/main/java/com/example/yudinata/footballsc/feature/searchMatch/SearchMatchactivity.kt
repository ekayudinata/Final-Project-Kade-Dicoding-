package com.example.yudinata.footballsc.feature.searchMatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import com.example.yudinata.footballsc.R
import com.example.yudinata.footballsc.feature.detail.MatchDetailActivity
import com.example.yudinata.footballsc.model.SearchMatchItem
import kotlinx.android.synthetic.main.activity_search_matchactivity.*
import org.jetbrains.anko.startActivity

class SearchMatchactivity : AppCompatActivity(),SearchMatchView {

    var present: SearchMatchPresenter? = null
    var adapterList: SearchMatchAdapter? = null
    var liga: MutableList<SearchMatchItem> = mutableListOf()
    var hasil: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_matchactivity)

        setSupportActionBar(toolbar)
        rv_list_match.layoutManager = LinearLayoutManager(this)

        present = SearchMatchPresenter(this)


        search_match.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {

                hasil = query!!
                hasil = hasil!!.replace(" ","_")

                if (query.length > 2) {
                    present?.getMatchSearch(hasil!!)
                    adapterList = SearchMatchAdapter(liga) {
                        startActivity<MatchDetailActivity>(
                                "idEvent" to "${it.idEvent}",
                                "idHomeTeam" to "${it.idHomeTeam}",
                                "idAwayTeam" to "${it.idAwayTeam}"
                        )
                    }
                    rv_list_match.adapter = adapterList
                }

                return true
            }
        })
    }

    override fun showEventList(data: List<SearchMatchItem>) {
        liga.clear()
        liga.addAll(data)
        adapterList?.notifyDataSetChanged()
    }

}
