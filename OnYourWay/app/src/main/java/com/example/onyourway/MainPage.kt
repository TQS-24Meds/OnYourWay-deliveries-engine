package com.example.onyourway

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.onyourway.databinding.FragmentLoginFragBinding
import com.example.onyourway.databinding.FragmentMainPageBinding
import kotlinx.coroutines.Job


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainPage : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMainPageBinding>(inflater,
            R.layout.fragment_main_page,container,false)
        binding.reqbuttn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.main_accept_request)
        }
        binding.madedeliveriesbtn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.main_rider_deliveries)
        }
        binding.qrcodebtn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.main_riderQR)
        }



        setHasOptionsMenu(true)
        return binding.root

    }

}

