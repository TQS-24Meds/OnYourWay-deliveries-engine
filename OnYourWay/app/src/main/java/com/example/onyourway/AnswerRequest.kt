package com.example.onyourway

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.onyourway.databinding.FragmentAnswerRequestBinding
import com.example.onyourway.databinding.FragmentLoginFragBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AnswerRequest.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnswerRequest : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentAnswerRequestBinding>(inflater,
            R.layout.fragment_answer_request,container,false)
        binding.productsbtn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_answerRequest_to_packageProducts)
        }
        binding.locationbtn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_answerRequest_to_mapsFragment)
        }
        binding.yesbtn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_answerRequest_to_requests)
        }
        binding.nobtn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_answerRequest_to_mainPage2)
        }


        setHasOptionsMenu(true)
        return binding.root

    }


}