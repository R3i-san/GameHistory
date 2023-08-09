package be.laurent.gamehistory.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.laurent.gamehistory.R
import be.laurent.gamehistory.adapter.PartyAdapter
import be.laurent.gamehistory.adapter.ScoreDetailsAdapter
import be.laurent.gamehistory.adapter.ScoreFieldsAdapter
import be.laurent.gamehistory.databinding.FragmentHomeBinding
import be.laurent.gamehistory.interfaces.IDisplayDetails
import be.laurent.gamehistory.viewmodels.HomeViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment(), IDisplayDetails {

    private lateinit var historyView : RecyclerView

    //val model = ViewModelProvider()[HomeViewModel::class.java]

    private val viewModel: HomeViewModel by activityViewModels()
    //TimeTableViewModel viewModel = new ViewModelProvider(this).get(TimeTableViewModel.class);

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = checkNotNull(_binding){
            "Cannot access binding because it is null. Is the view is Visible ?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.partyRecyclerView.layoutManager = LinearLayoutManager(context)
        historyView = binding.partyRecyclerView

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.parties.collect{
                        list -> binding.partyRecyclerView.adapter =
                    PartyAdapter(this@HomeFragment as IDisplayDetails, R.layout.item_party, list)
                }
            }
        }
    }

    override fun displayDetails(index : Int) {
        //Toast.makeText(context, "it works", Toast.LENGTH_SHORT).show()

        val builder = AlertDialog.Builder(requireContext())
        val party = viewModel.getPartyAt(index)
        val scores = viewModel.getScoresOf(index)

        val popup = layoutInflater.inflate(R.layout.popup_details, null)
        popup.findViewById<TextView>(R.id.details_description).text = party.description
        popup.findViewById<TextView>(R.id.details_timer).text = party.timer.toString()
        popup.findViewById<TextView>(R.id.details_location).text = party.location
        popup.findViewById<RecyclerView>(R.id.details_recycler_view).adapter =
            ScoreDetailsAdapter(R.layout.item_details, scores)

        with(builder)
        {
            /*setTitle("Details")
            setView(popup)
            setMessage("We have a second message")*/

            builder.setTitle("Details")

            builder.setView(popup)

            show()
        }

    }

}