package com.example.onyourway

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onyourway.databinding.FragmentDeliveriesBinding
import com.example.onyourway.room.DeliveryApplication
import com.example.onyourway.room.DeliveryViewModel
import com.example.onyourway.room.ViewModelFactory


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Deliveries : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var recyclerView: RecyclerView? = null
    lateinit var binding: FragmentDeliveriesBinding
    private val deliveryViewModel : DeliveryViewModel by viewModels {
        ViewModelFactory (( activity?.application as DeliveryApplication).repository)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentDeliveriesBinding>(inflater,
            R.layout.fragment_deliveries,container,false)
        // Inflate the layout for this fragment

        recyclerView = binding.recyclerview
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager =LinearLayoutManager(view?.context)
        val adapter = DeliveryAdapter()
        Log.d("myadapter",adapter.toString())
        recyclerView!!.adapter = adapter


        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        deliveryViewModel.allDeliveries.observe(viewLifecycleOwner) { delivery ->
            // Update the cached copy of the words in the adapter.
            delivery.let { adapter.submitList(it) }
        }
        return inflater.inflate(R.layout.fragment_deliveries, container, false)
    }
}

