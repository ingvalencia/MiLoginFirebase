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

class SignUpFragment(private val firebaseManager: FirebaseAuth): Fragment() {

  var navigationManager: NavigationManager? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
    view.findViewById<Button>(R.id.confirmButton).setOnClickListener {
      if (allFieldsAreFilled()) {
        addUser()
      } else {
        Toast.makeText(view.context, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
      }
    }
    return view
  }

  private fun addUser() {
    val email = view?.findViewById<EditText>(R.id.editTextNewUser)?.text.toString()
    val password = view?.findViewById<EditText>(R.id.editTextNewPassword)?.text.toString()
    firebaseManager.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
      if(it.isSuccessful) {
        Toast.makeText(view?.context, "Successful registration", Toast.LENGTH_SHORT).show()
        navigationManager?.presentSignInFragment()
      } else {
        Toast.makeText(view?.context, "We couldn't add this user", Toast.LENGTH_SHORT).show()
        navigationManager?.presentSignInFragment()
      }
    }
  }

  private fun allFieldsAreFilled(): Boolean {
    return view?.findViewById<EditText>(R.id.editTextNewUser)?.text.toString()
      .isNotEmpty() && view?.findViewById<EditText>(R.id.editTextNewPassword)?.text.toString()
      .isNotEmpty() && view?.findViewById<EditText>(R.id.editTextNewName)?.text.toString()
      .isNotEmpty() && view?.findViewById<EditText>(R.id.editTextNewLastName)?.text.toString()
      .isNotEmpty()
  }

}