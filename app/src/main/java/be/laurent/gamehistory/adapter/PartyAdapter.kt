package be.laurent.gamehistory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import be.laurent.gamehistory.MainActivity
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.R
import be.laurent.gamehistory.viewmodels.HomeViewModel

class PartyAdapter(
    private val context: MainActivity,
    private val layoutId: Int,
    private val parties: List<PartyModel>) : RecyclerView.Adapter<PartyAdapter.ViewHolder> () {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val partyItem = view.findViewById<TextView>(R.id.party_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentParty = parties[position]
        //TODO : rapid fix but it will create bugs in futur, please remain of it
        holder.partyItem.text = currentParty.description
        //Glide.with(context).load(Uri.parse(currentParty.getThumbnail())).into(holder.)
    }

    override fun getItemCount(): Int = parties.size


}