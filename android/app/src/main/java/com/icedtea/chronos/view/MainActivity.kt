package com.icedtea.chronos.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.icedtea.chronos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        // view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Go to Collection List on Click
        binding.collectionButton.setOnClickListener{
            val intent = Intent(this, CollectionActivity::class.java)
            startActivity(intent)
        }
        // Go to Watch Add form on Click
        binding.wishlistButton.setOnClickListener{
            val intent = Intent(this, AddFormActivity::class.java)
            startActivity(intent)
        }
    }
}