package be.laurent.gamehistory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.laurent.gamehistory.R
import be.laurent.gamehistory.models.ScoreModel

class ScoreAdapter(
    private val layoutId: Int,
    private val nbrScores : Int
) : RecyclerView.Adapter<ScoreAdapter.ViewHolder> () {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val scoreName = view.findViewById<TextView>(R.id.scoreName)
        val scoreValue = view.findViewById<TextView>(R.id.scoreValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}

    override fun getItemCount(): Int = nbrScores

}