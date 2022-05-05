package com.gvalencia.ejemplofirebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class SignInFragment(private val firebaseManager: FirebaseAuth) : Fragment() {

  var navigationManager: NavigationManager? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_sign_in, container, false)
    view.findViewById<Button>(R.id.signInButton).setOnClickListener {
      if (allFieldsAreFilled()) {
        login()
      } else {
        Toast.makeText(view.context, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
      }
    }
    view.findViewById<Button>(R.id.goToSignUpButton).setOnClickListener {
      presentSignUp()
    }
    return view
  }

  private fun presentSignUp() {
    navigationManager?.presentSignUpFragment()
  }

  private fun login() {
    val email = view?.findViewById<EditText>(R.id.editTextUser)?.text.toString()
    val password = view?.findViewById<EditText>(R.id.editTextPassword)?.text.toString()
    firebaseManager.signInWithEmailAndPassword(email, password).addOnCompleteListener {
      if(it.isSuccessful) {
        Toast.makeText(view?.context, "Succesful login", Toast.LENGTH_SHORT).show()
        navigationManager?.presentHomeFragment()
      } else {
        Toast.makeText(view?.context, "Check your password and email", Toast.LENGTH_SHORT).show()
      }
    }
  }

  private fun allFieldsAreFilled(): Boolean {
    return view?.findViewById<EditText>(R.id.editTextUser)?.text.toString()
      .isNotEmpty() && view?.findViewById<EditText>(R.id.editTextPassword)?.text.toString()
      .isNotEmpty()
  }

}