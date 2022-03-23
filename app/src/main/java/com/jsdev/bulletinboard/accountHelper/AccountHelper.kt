package com.jsdev.bulletinboard.accountHelper

import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseUser
import com.jsdev.bulletinboard.MainActivity
import com.jsdev.bulletinboard.R

class AccountHelper(private val act: MainActivity) {

    fun signUpWithEmail (email:String, password:String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            act.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    sentEmailVerification(task.result?.user!!)
                    act.uiUpdate(task.result?.user)

                }else {

                    Toast.makeText(act,act.resources.getString(R.string.sign_in_error),Toast.LENGTH_SHORT).show()

                }

            }
        }

    }

    fun signInWithEmail (email:String, password:String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            act.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    sentEmailVerification(task.result?.user!!)
                    act.uiUpdate(task.result?.user)

                }else {
                    Toast.makeText(act,act.resources.getString(R.string.sign_up_error),Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

    private fun sentEmailVerification(user: FirebaseUser) {
        user.sendEmailVerification().addOnCompleteListener { task ->

            if (task.isSuccessful) {

                Toast.makeText(act,act.resources.getString(R.string.send_verification_done), Toast.LENGTH_SHORT).show()

            } else {

                Toast.makeText(act,act.resources.getString(R.string.send_verification_email_error), Toast.LENGTH_SHORT).show()

            }

        }
    }

    private fun getSignInClient(): GoogleSignInAccount {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(act.getString(R.string.default_web_client_id))

    }

}