package com.jsdev.bulletinboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.jsdev.bulletinboard.databinding.ActivityMainBinding
import com.jsdev.bulletinboard.dialoghelper.DialogConst
import com.jsdev.bulletinboard.dialoghelper.DialogHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content.*

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var tvAccount: TextView
    private var rootElement: ActivityMainBinding? = null
    private val dialogHelper = DialogHelper(this)
    val  mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        rootElement = ActivityMainBinding.inflate(layoutInflater)
        val view = rootElement!!.root
        setContentView(view)
        init()
    }

    override fun onStart() {
        super.onStart()
        uiUpdate(mAuth.currentUser)
    }

    private fun init() {

        val toggle = ActionBarDrawerToggle(this,rootElement?.drawerLayout,rootElement?.mainContent?.toolbar,R.string.open,R.string.close)
        rootElement?.drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()
        rootElement?.navView?.setNavigationItemSelectedListener (this)
        tvAccount = rootElement?.navView?.getHeaderView(0)!!.findViewById(R.id.tvAccountEmail)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.cat_ad -> {

            }
            R.id.cat_car -> {

            }
            R.id.cat_pc -> {

            }
            R.id.cat_smartphone -> {

            }
            R.id.cat_appliances -> {

            }
            R.id.acc_signIn -> {

                dialogHelper.createSignDialog(DialogConst.SIGN_IN_STATE)

            }
            R.id.acc_logIn -> {

                dialogHelper.createSignDialog(DialogConst.SIGN_UP_STATE)

            }
            R.id.acc_logOut -> {
                uiUpdate(null)
                mAuth.signOut()
            }

        }
        rootElement?.drawerLayout?.closeDrawer(GravityCompat.START)
        return true
    }

    fun uiUpdate(user: FirebaseUser?){
        tvAccount.text = if (user == null) {
            resources.getString(R.string.not_registration)
        } else {
            user.email
        }
    }

}