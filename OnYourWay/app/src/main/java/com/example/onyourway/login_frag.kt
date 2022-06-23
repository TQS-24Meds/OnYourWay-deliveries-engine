package com.example.onyourway

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.onyourway.databinding.FragmentLoginFragBinding
import com.example.onyourway.room.DeliveryApplication
import com.example.onyourway.room.DeliveryViewModel
import com.example.onyourway.room.Rider
import com.example.onyourway.room.ViewModelFactory
import retrofit2.Call
import java.lang.Exception


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [login_frag.newInstance] factory method to
 * create an instance of this fragment.
 */class LoginFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private val deliveryViewModel : DeliveryViewModel by viewModels {
        ViewModelFactory (( activity?.application as DeliveryApplication).repository)
    }

    lateinit var binding: FragmentLoginFragBinding
    var flag : Boolean = true
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentLoginFragBinding>(inflater,
            R.layout.fragment_login_frag,container,false)
        binding.btnLogin.setOnClickListener { view:View->

            if (checkdata()) {
                var loginRequest: LoginRequest = LoginRequest()

                loginRequest.email= binding.etEmail.toString()
                loginRequest.password= binding.etPassword.toString()
                try {
                    Log.e("rider","2fk")

                    deliveryViewModel.getRiderWithEmail(binding.etEmail.toString())
                    val rider= deliveryViewModel.rider
                    Log.e("rider",rider.toString())

                    val preferences = this.activity!!
                        .getSharedPreferences("pref", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor =  preferences.edit()
                    editor.apply()
                    editor.commit()
                    loginUser(loginRequest)
                }catch (e:Exception){
                    Toast.makeText(activity, "BadCredentials", Toast.LENGTH_SHORT)

                }



            }
        }
        binding.swapRegister.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_login_frag_to_signUp)
        }

        setHasOptionsMenu(true)
        return binding.root

    }

    fun loginUser(loginRequest: LoginRequest ) {
        val loginResponseCall: Call<LoginResponse?>? =
            APIClient.service.loginUser(loginRequest);
        loginRequest.let {
            val amount = binding.etEmail.text.toString()
            val action = LoginFragDirections.loginToMain(amount)

            view?.findNavController()?.navigate(action)
        } ?: run{
            var t:Throwable = Throwable()
            var msg= t.localizedMessage
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT)

        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun isEmail(text: EditText?): Boolean {
        val email: CharSequence = text!!.text.toString()
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isEmpty(text: EditText?): Boolean {
        val str: CharSequence = text!!.text.toString()
        return TextUtils.isEmpty(str)
    }
    private fun checkdata(): Boolean {
        flag =true
        if (isEmpty(binding.etEmail)) {
            val t =
                Toast.makeText(activity, "You must enter email to Login !", Toast.LENGTH_SHORT)
            t.show()
            flag=false
        }
        if (isEmpty(binding.etPassword)) {
            binding.etPassword!!.error = "Password   is required!"
            flag=false
        }
        if (!isEmail(binding.etEmail)) {
            binding.etEmail!!.error = "Enter valid email!"
            flag=false
        }
        return flag
    }








}
