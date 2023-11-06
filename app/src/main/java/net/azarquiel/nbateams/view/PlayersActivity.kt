package net.azarquiel.nbateams.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.nbateams.R
import net.azarquiel.nbateams.model.Player
import net.azarquiel.nbateams.model.Team
import net.azarquiel.recyclerviewpajaros.adapter.CustomAdapterP
import java.net.URL

class PlayersActivity : AppCompatActivity() {
    private lateinit var team:Team
    lateinit var adapter: CustomAdapterP
    lateinit var rvplayer: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)
        team =intent.getSerializableExtra("team") as Team

        initRV()
        getDatos()

    }

    private fun getDatos() {
        GlobalScope.launch() {
            val jsontxt = URL("http://www.ies-azarquiel.es/paco/apinba/players/team?name=" + "${team.name}"
            ).readText(Charsets.UTF_8)
            launch(Dispatchers.Main) {
                val playersarray = Gson().fromJson(jsontxt, Array<Player>::class.java)
                for (player in playersarray) {
                    Log.d("JUGADOR", player.toString())
                }
                adapter.setPlayers(playersarray.toList())
            }
        }
    }

    private fun initRV() {
        rvplayer = findViewById<RecyclerView>(R.id.rvplayer)
        adapter = CustomAdapterP(this, R.layout.rowplayer)
        rvplayer.adapter = adapter
        rvplayer.layoutManager = LinearLayoutManager(this)


    }
}