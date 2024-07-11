package com.umeshdev.kmmfirebaseotppoc.android

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.umeshdev.kmmfirebaseotppoc.android.databinding.ActivityVerifyOtpBinding
import com.umeshdev.kmmfirebaseotppoc.auth.FirebaseRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.PhoneAuthProvider
import dev.gitlive.firebase.auth.PhoneVerificationProvider
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.initialize

class VerifyOtpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerifyOtpBinding
    private var storedVerificationId: String = ""
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseRepository: FirebaseRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        Firebase.initialize(this)
        firebaseRepository =
            FirebaseRepository(phoneAuthProvider = PhoneAuthProvider(Firebase.auth))

        storedVerificationId = intent.getStringExtra("storedVerificationId").toString()

        binding.verifyOtp.setOnClickListener {


            firebaseRepository.verifyOtp(storedVerificationId,
                binding.otpNumber.text.toString(),
                onSuccess = { authCredential ->
                    // Handle successful verification

                    Toast.makeText(this, "Success" + authCredential, Toast.LENGTH_SHORT).show()

                    Log.d(
                        "MainActivity",
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