package be.laurent.gamehistory.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import be.laurent.gamehistory.MainActivity
import be.laurent.gamehistory.models.GameModel
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.R
import be.laurent.gamehistory.adapter.PartyAdapter
import be.laurent.gamehistory.databinding.FragmentHomeBinding
import be.laurent.gamehistory.viewmodels.AddPartyViewModel
import be.laurent.gamehistory.viewmodels.HomeViewModel
import kotlin.math.log

class HomeFragment() : Fragment() {

    private lateinit var historyView : RecyclerView

    private lateinit var binding : FragmentHomeBinding
    lateinit var context: MainActivity
    lateinit var viewModel: HomeViewModel

    /*companion object {

        fun initFragment(ctx:MainActivity, vm:HomeViewModel) : HomeFragment{

            val frag = HomeFragment()
            frag.context = ctx
            frag.viewModel = vm

            return frag
        }
    }*/


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        binding = FragmentHomeBinding.inflate(layoutInflater)

        //historyView = binding.partyRecyclerView
        historyView = view.findViewById(R.id.party_recycler_view)

        //viewModel.parties.observe(viewLifecycleOwner) { feedRecycler() }

        return view
    }

    private fun feedRecycler(){
        viewModel.getNbrParties()
        historyView.adapter = PartyAdapter(context, R.layout.item_party, viewModel.getParties())
    }




}