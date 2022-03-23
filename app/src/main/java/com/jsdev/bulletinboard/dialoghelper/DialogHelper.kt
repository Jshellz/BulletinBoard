package com.jsdev.bulletinboard.dialoghelper

import android.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.jsdev.bulletinboard.MainActivity
import com.jsdev.bulletinboard.R
import com.jsdev.bulletinboard.accountHelper.AccountHelper
import com.jsdev.bulletinboard.databinding.SignInDialogBinding
import kotlin.concurrent.timerTask

class DialogHelper(private val act: MainActivity) {

    private val accHelper =  AccountHelper(act)

    fun createSignDialog(index:Int) {

        val builder = AlertDialog.Builder(act)
        val rootDialogElement = SignInDialogBinding.inflate(act.layoutInflater)
        val view = rootDialogElement.root
        builder.setView(view)
        setDialogState(index,rootDialogElement)

        val dialog = builder.create()
        rootDialogElement.btnSignIn.setOnClickListener {
            setOnClickSignUpIn(index, rootDialogElement,dialog)

        }

        rootDialogElement.btnPasswordReset.setOnClickListener {
            setOnClickResetPassword(rootDialogElement,dialog)

        }
        dialog.show()
    }

    private fun setOnClickResetPassword(
        rootDialogElement: SignInDialogBinding,
        dialog: AlertDialog?,
    ) {
        if (rootDialogElement.edNameDialog.text.isNotEmpty()) {
            act.mAuth.sendPasswordResetEmail(rootDialogElement.edNameDialog.text.toString()).addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    Toast.makeText(act,R.string.email_reset_password_was_sent, Toast.LENGTH_SHORT).show()
                    dialog?.dismiss()

                } else {

                    rootDialogElement.tvDialogMessage.visibility = View.VISIBLE

                }

            }
        }
    }

    private fun setOnClickSignUpIn(
        index: Int,
        rootDialogElement: SignInDialogBinding,
        dialog: AlertDialog?,
    ) {
        dialog?.dismiss()

        if (index == DialogConst.SIGN_UP_STATE) {

            accHelper.signUpWithEmail(
                rootDialogElement.edNameDialog.text.toString(),
                rootDialogElement.etPasswordDialog.text.toString())

        } else {

            accHelper.signUpWithEmail(
                rootDialogElement.edNameDialog.text.toString(),
                rootDialogElement.etPasswordDialog.text.toString()
            )

        }
    }

    private fun setDialogState(index: Int, rootDialogElement: SignInDialogBinding) {

        if (index == DialogConst.SIGN_UP_STATE) {
            rootDialogElement.tvSignIn.text = act.resources.getString(R.string.tv_sign_up_title)
            rootDialogElement.btnSignIn.text = act.resources.getString(R.string.btn_sign_up_action)

        } else {

            rootDialogElement.tvSignIn.text = act.resources.getString(R.string.tv_sign_title)
            rootDialogElement.btnSignIn.text = act.resources.getString(R.string.btn_sign_in_action)
            rootDialogElement.btnPasswordReset.visibility = View.VISIBLE

        }

    }

}