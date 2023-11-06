package net.azarquiel.recyclerviewpajaros.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.nbateams.R
import net.azarquiel.nbateams.model.Player

/**
 * Created by pacopulido on 9/10/18.
 */
class CustomAdapterP(val context: Context,
                     val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapterP.ViewHolder>() {

    private var dataList: List<Player> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setPlayers(Players: List<Player>) {
        this.dataList = Players
        notifyDataSetChanged()
    }



    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Player){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val ivrowPlayer = itemView.findViewById(R.id.ivrowPlayer) as ImageView
            val tvrowPlayer = itemView.findViewById(R.id.tvrowPlayer) as TextView

            tvrowPlayer.text = "${dataItem.firstName}% ${dataItem.lastName}%"


            // foto de internet a traves de Picasso
            val foto = "${dataItem.headShotUrl}"
            Picasso.get().load(foto).into(ivrowPlayer)
            itemView.tag = dataItem

        }

    }
}