package com.umeshdev.kmmfirebaseotppoc.android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.umeshdev.kmmfirebaseotppoc.AndroidPhoneVerificationProvider
import com.umeshdev.kmmfirebaseotppoc.android.databinding.ActivitySendOtpBinding
import com.umeshdev.kmmfirebaseotppoc.auth.FirebaseRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.PhoneAuthProvider
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.initialize

class SendOtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySendOtpBinding
    private lateinit var firebaseRepository: FirebaseRepository
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        Firebase.initialize(this)
        firebaseRepository =
            FirebaseRepository(phoneAuthProvider = PhoneAuthProvider(Firebase.auth))
        val phoneVerificationProvider = AndroidPhoneVerificationProvider(this)


        binding.sendOtp.setOnClickListener {


            firebaseRepository.verifyNumber("+91" + binding.phoneNumber.text.toString(),
                phoneVerificationProvider,
                onSuccess = { authCredential ->
                    // Handle successful verification
                    val intent = Intent(this, VerifyOtpActivity::class.java)
                    intent.putExtra("storedVerificationId", authCredential.toString())
                    startActivity(intent)

                    Toast.makeText(this, "Success" + authCredential.toString(), Toast.LENGTH_SHORT)
                        .show()

                    Log.d(
                        "MainActivity1",
                        "Verification successful with providerId: ${authCredential.providerId}"
                    )
                },
                onFailure = { exception ->
                    // Handle verification failure
                    Log.e("MainActivity", "Verification failed", exception)
                })

        }
    }


}