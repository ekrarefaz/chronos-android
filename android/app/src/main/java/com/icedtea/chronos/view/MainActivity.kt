package com.icedtea.chronos.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.icedtea.chronos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.collectionButton.setOnClickListener{
            val intent = Intent(this, CollectionActivity::class.java)
            startActivity(intent)
        }
        binding.wishlistButton.setOnClickListener{
            val intent = Intent(this, AddFormActivity::class.java)
            startActivity(intent)
        }
    }
}