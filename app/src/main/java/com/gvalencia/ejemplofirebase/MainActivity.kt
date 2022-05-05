package com.gvalencia.ejemplofirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity(), NavigationManager {

    lateinit var authentication: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authentication = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = authentication.currentUser

        if (user == null) {
            presentSignInFragment()
        } else {
            presentHomeFragment()
        }
    }

    override fun presentSignInFragment() {
        val signInFragment: SignInFragment = SignInFragment(authentication)
        signInFragment.navigationManager = this
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerView, signInFragment)
            .commit()
    }

    override fun presentHomeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerView, HomeFragment())
            .commit()
    }

    override fun presentSignUpFragment() {
        val signUpFragment: SignUpFragment = SignUpFragment(authentication)
        signUpFragment.navigationManager = this
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerView, signUpFragment)
            .commit()
    }
}