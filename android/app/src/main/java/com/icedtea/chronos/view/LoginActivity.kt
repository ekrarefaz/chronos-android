package com.icedtea.chronos.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.icedtea.chronos.R
import com.icedtea.chronos.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.login_button)
        val emailText = findViewById<TextInputEditText>(R.id.watch_name).text
        val passText = findViewById<TextInputEditText>(R.id.pass_text).text
        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginButton.setOnClickListener{
            Log.i("LOGIN", "$emailText")
            Log.i("LOGIN", "$passText")
            viewModel.loginAttempt(emailText.toString(), passText.toString())
            val status = viewModel.loginStatus
            if (status){
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }
            else{
                Toast.makeText(applicationContext,"Login Failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}