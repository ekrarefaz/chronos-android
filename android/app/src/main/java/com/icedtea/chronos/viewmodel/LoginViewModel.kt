package com.icedtea.chronos.viewmodel
import android.text.Editable
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel:ViewModel() {
    private lateinit var auth: FirebaseAuth
    var loginStatus: Boolean = false
    fun loginAttempt(email: String, pass: String){
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            Log.i("LOGIN", "${task.exception}")
            loginStatus = true
        }
    }
}
