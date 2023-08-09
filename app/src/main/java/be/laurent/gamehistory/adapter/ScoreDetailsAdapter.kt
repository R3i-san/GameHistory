package be.laurent.gamehistory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.laurent.gamehistory.R
import be.laurent.gamehistory.models.ScoreModel

class ScoreDetailsAdapter(
    private val layoutId: Int,
    private val scores : List<ScoreModel>
) : RecyclerView.Adapter<ScoreDetailsAdapter.ViewHolder> () {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.DetailsScoreName)
        val value: TextView = view.findViewById(R.id.DetailsScoreValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = scores[position].name
        holder.value.text = scores[position].value.toString()
    }

    override fun getItemCount(): Int = scores.size

}