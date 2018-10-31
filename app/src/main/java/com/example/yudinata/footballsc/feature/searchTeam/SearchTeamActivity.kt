package com.example.yudinata.footballsc.feature.searchTeam

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import com.example.yudinata.footballsc.R
import com.example.yudinata.footballsc.feature.teamsDetail.TeamDetailActivity
import com.example.yudinata.footballsc.model.TeamsItem
import kotlinx.android.synthetic.main.activity_search_team.*
import org.jetbrains.anko.startActivity

class SearchTeamActivity : AppCompatActivity(),SearchTeamView {

    var presenter: SearchTeamPresenter? = null
    var adapter: SearchTeamAdapter? = null
    var teams: MutableList<TeamsItem> = mutableListOf()
    var hasil: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        rv_list_match.layoutManager = LinearLayoutManager(this)

        presenter = SearchTeamPresenter(this)


        search_match.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }
            override fun onQueryTextChange(query: String?): Boolean {
                hasil = query!!

                if (query.length > 2) {
                    presenter?.getMatchSearch(hasil!!)

                    adapter = SearchTeamAdapter(teams) {
                        startActivity<TeamDetailActivity>(
                                "idTeam" to "${it.idTeam}",
                                "nameTeam" to "${it.strTeam}",
                                "badgeTeam" to "${it.strTeamBadge}",
                                "formedYear" to "${it.intFormedYear}",
                                "stadium" to "${it.strStadium}"
                        )
                    }
                    rv_list_match.adapter = adapter
                }
                return true
            }
        })
    }

    override fun showEventList(data: List<TeamsItem>) {
        teams.clear()
        teams.addAll(data)
        adapter?.notifyDataSetChanged()
    }
}
