package be.laurent.gamehistory.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import be.laurent.gamehistory.R
import be.laurent.gamehistory.models.PartyScoresModel

class PartyAdapter(
    private val context: Context,
    private val layoutId: Int,
    private val parties: List<PartyScoresModel>
    ) : RecyclerView.Adapter<PartyAdapter.ViewHolder> () {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val partyName = view.findViewById<TextView>(R.id.party_name)
        val partyDescription = view.findViewById<TextView>(R.id.partyDescription)
        val detailsButton = view.findViewById<ImageButton>(R.id.gameDetails)
        val partyThumbnail = view.findViewById<ImageView>(R.id.party_thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentParty = parties[position]
        holder.partyName.text = currentParty.party.gameID
        holder.partyDescription.text = currentParty.party.description
        holder.detailsButton.setOnClickListener{setButtonAction()}
        loadThumbnail(holder, currentParty)

    }

    private fun loadThumbnail(holder: ViewHolder, currentParty : PartyScoresModel){

        if(currentParty.party.thumbnail.isEmpty()) return

        val imageView = holder.partyThumbnail
        val bytes = currentParty.party.thumbnail
        val bmp : Bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size);
        imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, 256, 256, false))
    }

    private fun setButtonAction()  {
        Toast.makeText(context, "Not implemented yet", Toast.LENGTH_SHORT).show()
    }

    override fun getItemCount(): Int = parties.size


}