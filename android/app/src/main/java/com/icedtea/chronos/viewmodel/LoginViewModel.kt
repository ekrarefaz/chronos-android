package com.icedtea.chronos.viewmodel
import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel:ViewModel() {
    private lateinit var auth: FirebaseAuth
    var authState = "idle"

    fun loginAttempt(email: String, pass: String){
        // Firebase Auth API instance
        auth = Firebase.auth
        // Authentication
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            authState = "success"
            Log.i("LOGIN", "$authState")
        }.addOnFailureListener {
            authState = "error"
            Log.i("LOGIN", "$authState")
            Log.i("LOGIN", "$it")
        }
    }
}
