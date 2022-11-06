package com.icedtea.chronos.view

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.icedtea.chronos.R
import com.icedtea.chronos.databinding.ActivityAddFormBinding
import com.icedtea.chronos.model.WatchDataClass
import com.icedtea.chronos.viewmodel.FormViewModel
import java.util.jar.Manifest

class AddFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding to Layout Elements
        binding = ActivityAddFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // ViewModel
        val viewModel = ViewModelProvider(this).get(FormViewModel::class.java)
        // Get Current user
        val share = applicationContext.getSharedPreferences("currentUser", MODE_PRIVATE)
        val user = share.getString("userEmail",null)
        Log.i("LOGIN", "$user")
        binding.addWatchBtn.setOnClickListener {
            val newWatch = WatchDataClass(
                binding.watchCode.text.toString(),
                binding.watchBrand.text.toString(),
                binding.watchName.text.toString(),
                binding.watchDial.text.toString(),
                binding.watchColor.text.toString(),
                binding.price.text.toString()
            )
            // Debug
            Log.i("DATABASE", "$newWatch")

            // Checking if a current user session in place
            // If accessed directly without user login can't update database for security
            if (user != null) {
                val status = viewModel.addWatchData(newWatch, user)
                // Display Toast to Notify
                if (status == "SUCCESS") {
                    Toast.makeText(
                        applicationContext,
                        "Watch Added Successfully",
                        Toast.LENGTH_LONG
                    ).show()

                    // Go to Collection on Successful Watch Addition
                    Intent(this, CollectionActivity::class.java).apply {
                        startActivity(this)
                    }
                }
                else{
                    Toast.makeText(applicationContext,"Proper Data Input Required", Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(applicationContext,"Watch Addition Unsuccessful", Toast.LENGTH_LONG).show()
                Log.i("DATABASE", "Empty User")
            }
        }
    }
}