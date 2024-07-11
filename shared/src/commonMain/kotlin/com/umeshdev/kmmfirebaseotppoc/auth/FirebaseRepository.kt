package com.umeshdev.kmmfirebaseotppoc.auth


import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.PhoneAuthProvider
import dev.gitlive.firebase.auth.PhoneVerificationProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirebaseRepository(
    private val phoneAuthProvider: PhoneAuthProvider

) {

    fun verifyNumber(
        phoneNumber: String,
        verificationProvider: PhoneVerificationProvider,
        onSuccess: (AuthCredential) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val credential = phoneAuthProvider.verifyPhoneNumber(phoneNumber, verificationProvider)

                onSuccess(credential)


            } catch (e: Exception) {
                onFailure(e)
            }
        }

    }

    fun verifyOtp(
        verificationId: String,
        smsCode: String,
        onSuccess: (AuthCredential) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val credential = phoneAuthProvider.credential(verificationId, smsCode)
                onSuccess(credential)
            } catch (e: Exception) {
                onFailure(e)
            }
        }
    }


}