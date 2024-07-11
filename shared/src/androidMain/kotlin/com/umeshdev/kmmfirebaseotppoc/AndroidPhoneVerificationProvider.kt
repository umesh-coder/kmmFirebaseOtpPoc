package com.umeshdev.kmmfirebaseotppoc

import android.app.Activity
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.PhoneVerificationProvider
import kotlinx.coroutines.CompletableDeferred
import java.util.concurrent.TimeUnit

class AndroidPhoneVerificationProvider(
    override val activity: Activity,
    override val timeout: Long = 60,
    override val unit: TimeUnit = TimeUnit.SECONDS
) : PhoneVerificationProvider {


    override fun codeSent(triggerResend: (Unit) -> Unit) {
//        TODO("Not yet implemented")
    }

    override suspend fun getVerificationCode(): String {
//        TODO("Not yet implemented")
        return "123456"
    }
}

