package net.azarquiel.nbateams.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.nbateams.R
import net.azarquiel.nbateams.model.Player
import net.azarquiel.nbateams.model.Team
import net.azarquiel.recyclerviewpajaros.adapter.CustomAdapter
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var adapter: CustomAdapter
    lateinit var rvteam: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRV()
        getDatos()

    }

    private fun getDatos() {
        GlobalScope.launch() {
            val jsontxt = URL("http://www.ies-azarquiel.es/paco/apinba/teams").readText(Charsets.UTF_8)
            launch(Dispatchers.Main) {
                val teamsarray = Gson().fromJson(jsontxt, Array<Team>::class.java)
                for (team in teamsarray) {
                    Log.d("EQUIPO", team.toString())
                }

                adapter.setTeams(teamsarray.toList())
            }
        }
    }

    private fun initRV() {
        rvteam = findViewById<RecyclerView>(R.id.rvteam)
        adapter = CustomAdapter(this, R.layout.rownba)
        rvteam.adapter = adapter
        rvteam.layoutManager = LinearLayoutManager(this)


    }

    fun onclickteam(v: View){
        val team = v.tag as Team
        val intent = Intent(this, PlayersActivity::class.java)
        intent.putExtra("team", team)
        startActivity(intent)

    }

}