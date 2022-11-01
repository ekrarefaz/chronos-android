package com.icedtea.chronos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.icedtea.chronos.databinding.ActivityAddFormBinding
import com.icedtea.chronos.model.WatchDataClass
import com.icedtea.chronos.viewmodel.FormViewModel

class AddFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newWatch = WatchDataClass(
            binding.watchName.text.toString(),
            binding.watchDes.text.toString(),
            binding.watchDial.text.toString(),
            binding.watchColor.text.toString(),
            binding.price.text.toString()
        )
        val viewModel = ViewModelProvider(this).get(FormViewModel::class.java)
        binding.addWatchBtn.setOnClickListener {
            Log.i("View", "Calling Function")
            viewModel.addWatchData(newWatch)
        }

    }
}