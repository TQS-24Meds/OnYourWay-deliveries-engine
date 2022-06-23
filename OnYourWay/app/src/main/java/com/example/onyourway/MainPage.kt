package com.example.onyourway

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
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
    val args: MainPageArgs by navArgs()
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
        val viewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)
        binding.myVM = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        createChannel(
            getString(R.string.egg_notification_channel_id),
            getString(R.string.egg_notification_channel_name)
        )

        val amount = args.username
        Log.d("manooo",
            args.username)
        binding.ridername.text = amount


        setHasOptionsMenu(true)
        return binding.root

    }
    private fun createChannel(channelId: String, channelName: String) {
        // TODO: Step 1.6 START create a channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                // TODO: Step 2.4 change importance
                NotificationManager.IMPORTANCE_HIGH
            )// TODO: Step 2.6 disable badges for this channel
                .apply {
                    setShowBadge(false)
                }
                notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.newdel)

            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)

        }
    }
}

