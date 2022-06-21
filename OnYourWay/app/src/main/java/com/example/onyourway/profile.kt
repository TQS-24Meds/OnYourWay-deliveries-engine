package com.example.onyourway

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.onyourway.databinding.FragmentProfileBinding
import com.example.onyourway.databinding.FragmentSignUpBinding
import com.google.android.material.color.MaterialColors

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class profile : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentProfileBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentProfileBinding>(inflater,
            R.layout.fragment_profile,container,false)


        binding.statusbtn.setOnClickListener { view:View->

            if(binding.statusbtn.text=="Offline"){
                binding.statusbtn.setText("Available")
                binding.statusbtn.setBackgroundColor(Color.rgb(11,185,5))

            }else{
                binding.statusbtn.setText("Offline")
                binding.statusbtn.setBackgroundColor(Color.RED)
            }


        }


        return binding.root

    }

}
