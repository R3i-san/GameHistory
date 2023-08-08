package be.laurent.gamehistory.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import be.laurent.gamehistory.MainActivity
import be.laurent.gamehistory.models.PartyModel
import be.laurent.gamehistory.R
import be.laurent.gamehistory.adapter.PartyAdapter
import be.laurent.gamehistory.databinding.FragmentHomeBinding
import be.laurent.gamehistory.viewmodels.HomeViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

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
                    PartyAdapter(requireContext(), R.layout.item_party, list)
                }
            }
        }
    }
}