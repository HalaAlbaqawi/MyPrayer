package com.example.prayerproject.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.prayerproject.adapter.DuaaAdapter
import com.example.prayerproject.databinding.FragmentDuaaBinding

private const val TAG = "DuaaFragment"

class DuaaFragment : Fragment() {

    private lateinit var binding: FragmentDuaaBinding
    private lateinit var duaaAdapter: DuaaAdapter
    private val duaaViewModel: DuaaViewModel by activityViewModels()
    private val myFavoriteDuaaViewModel: MyFavoriteDuaaViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDuaaBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "duaa fragment")
        observers()

        duaaViewModel.callData()

        duaaAdapter = DuaaAdapter(duaaViewModel, myFavoriteDuaaViewModel, requireContext())
        binding.duaaRecyclerView.adapter = duaaAdapter


    }

    // observes the live data in the viewModel
    fun observers() {
        duaaViewModel.duaaLiveData.observe(viewLifecycleOwner, {
            duaaAdapter.submitList(it)
            binding.progressBarDuaa.animate().alpha(0f)

        })


    }
}