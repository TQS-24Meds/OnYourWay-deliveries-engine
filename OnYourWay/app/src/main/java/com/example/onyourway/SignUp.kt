package com.example.onyourway

import android.os.Bundle
import android.provider.CallLog
import android.telephony.PhoneNumberUtils.isGlobalPhoneNumber
import android.text.TextUtils
import android.util.Log

import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.onyourway.databinding.FragmentSignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUp.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUp : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var flag : Boolean = true

    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentSignUpBinding>(inflater,
            R.layout.fragment_sign_up,container,false)

        binding.swapLogin.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_signUp_to_login_frag)
        }
        binding.btnRegister.setOnClickListener { view:View->

            if (checkdata()) {
                var registerRequest: RegisterRequest = RegisterRequest()

                registerRequest.address= binding.address.toString()
                registerRequest.email= binding.etEmail.toString()
                registerRequest.name= binding.etName.toString()
                registerRequest.phone = binding.etPhonenumber.text.toString().toInt()
                registerRequest.username= binding.etUsername.toString()
                registerRequest.password= binding.etPassword.toString()
                registerUser(registerRequest)
            }
        }


        setHasOptionsMenu(true)
        return binding.root

    }
    fun registerUser(registerRequest:RegisterRequest, ) {
        val registerResponseCall: Call<RegisterResponse?>? =
            APIClient.service.registerUser(registerRequest);
        registerRequest.let {
            view?.findNavController()?.navigate(R.id.action_signUp_to_login_frag)
        } ?: run{
            var t:Throwable = Throwable()
            var msg= t.localizedMessage
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT)

        }

    }




    private fun isEmail(text: EditText?): Boolean {
        val email: CharSequence = text!!.text.toString()
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isPhone(etPhonenumber: EditText): Boolean {
        Log.d("tag",
            binding.etPhonenumber.text.toString())

        return !isGlobalPhoneNumber(isGlobalPhoneNumber(binding.etPhonenumber.toString()).toString())
    }

    private fun isEmpty(text: EditText?): Boolean {
        val str: CharSequence = text!!.text.toString()
        return TextUtils.isEmpty(str)
    }
    private fun checkdata(): Boolean {
        flag= true
        if (isEmpty(binding.etName)) {

            binding.etName!!.error = "Name is required!"
            flag=false
        }
        if (isEmpty(binding.etUsername)) {

            binding.etUsername!!.error = "Username is required!"
            flag=false
        }

        if (!isPhone(binding.etPhonenumber)) {

            binding.etPhonenumber!!.error = "Enter valid phone!"
            flag=false
        }


        if (isEmpty(binding.address)) {

            binding.address!!.error = "Address is required!"
            flag=false
        }
        if (isEmpty(binding.etPassword)) {

            binding.etPassword!!.error = "Password is required!"
            flag=false
        }
        if (!isEmail(binding.etEmail)) {
            binding.etEmail!!.error = "Enter valid email!"
            flag=false
        }
        if (!flag){
            val t =
                Toast.makeText(activity, "You must fill all required fields to Register !", Toast.LENGTH_SHORT)
            t.show()
        }
        return flag
    }

}
