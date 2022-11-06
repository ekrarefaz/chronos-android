package com.icedtea.chronos.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.icedtea.chronos.R
import com.icedtea.chronos.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Store the Current User in SharedPref
        val share = getSharedPreferences("currentUser", MODE_PRIVATE)
        val editor = share.edit()

        val loginButton = findViewById<Button>(R.id.login_button)
        val emailText = findViewById<TextInputEditText>(R.id.email_text).text

        val passText = findViewById<TextInputEditText>(R.id.pass_text).text
        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginButton.setOnClickListener{
            Log.i("LOGIN", "$emailText")
            Log.i("LOGIN", "$passText")

            // Validating Input
            val validate = viewModel.validate(emailText.toString().trim(), passText.toString().trim())

            // Calling Login Function from Viewmodel
            if(validate) {
                viewModel.loginAttempt(emailText.toString().trim(), passText.toString().trim())
                Log.i("LOGIN", "${viewModel.authState}")
            }
            else{
                Toast.makeText(applicationContext,"Login Failed", Toast.LENGTH_LONG).show()
            }
            // Checking if Login Success
            if (viewModel.authState == "success"){

                // Store userEmail in SharedPref
                editor.apply {
                    putString("userEmail", "${emailText.toString().trim()}")
                    commit()
                }
                // Go to main on Success
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