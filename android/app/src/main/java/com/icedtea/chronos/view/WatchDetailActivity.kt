package com.icedtea.chronos.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.FirebaseDatabase
import com.icedtea.chronos.R
import com.icedtea.chronos.databinding.ActivityAddFormBinding
import com.icedtea.chronos.databinding.ActivityWatchDetailBinding
import com.icedtea.chronos.model.WatchDataClass
import com.icedtea.chronos.viewmodel.WatchDetailViewModel

class WatchDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWatchDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Getting the selected Watch
        val watchSelected = intent.getParcelableExtra<WatchDataClass>("watchSelected")
        // DEBUG
        Log.i("INTENT", "$watchSelected")
        // Setting Watch Text
        binding.watchCode.text = watchSelected?.watchCode.toString()
        binding.watchBrand.text = watchSelected?.watchBrand.toString()
        binding.watchName.text = watchSelected?.watchName.toString()
        binding.watchDialSize.text = watchSelected?.watchDiaSize.toString()
        binding.watchDialColor.text = watchSelected?.watchDialColor.toString()
        binding.watchPrice.text = watchSelected?.watchPrice.toString()
    }
    // Options Menu for Edit
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_menu,menu)
        return true
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.edit -> {
                convertToEdit()
            }
            R.id.saved -> {
                saveToDatabase()
            }
            R.id.delete->{
                deleteFromDatabase()
            }
        }
        return true
    }
    // Sets Visibility of TextView and EditText
    private fun convertToEdit(){
        binding.watchBrand.visibility = View.GONE
        binding.watchName.visibility = View.GONE
        binding.watchDialSize.visibility = View.GONE
        binding.watchDialColor.visibility = View.GONE
        binding.watchPrice.visibility = View.GONE

        binding.watchBrandEdit.visibility = View.VISIBLE
        binding.watchNameEdit.visibility = View.VISIBLE
        binding.watchDialSizeEdit.visibility = View.VISIBLE
        binding.watchDialColorEdit.visibility = View.VISIBLE
        binding.watchPriceEdit.visibility = View.VISIBLE

        binding.watchBrandEdit.hint = binding.watchBrand.text.toString()
        binding.watchNameEdit.hint = binding.watchName.text.toString()
        binding.watchDialSizeEdit.hint = binding.watchDialSize.text.toString()
        binding.watchDialColorEdit.hint = binding.watchDialColor.text.toString()
        binding.watchPriceEdit.hint = binding.watchPrice.text.toString()
    }
    // Make Changes to database with new data
    private fun saveToDatabase(){
        val viewModel = ViewModelProvider(this).get(WatchDetailViewModel::class.java)
        val watch = WatchDataClass(
            binding.watchCode.text.toString(),
            binding.watchBrandEdit.text.toString(),
            binding.watchNameEdit.text.toString(),
            binding.watchDialSizeEdit.text.toString(),
            binding.watchDialColorEdit.text.toString(),
            binding.watchPriceEdit.text.toString()
        )
        // Updating Database from ViewModel
        viewModel.updateDatabase(watch, binding.watchCode.text.toString())
        Toast.makeText(applicationContext,"Update Success", Toast.LENGTH_LONG).show()

        // Go to Main Menu
        onBackPressed()
    }
    private fun deleteFromDatabase(){
        val viewModel = ViewModelProvider(this).get(WatchDetailViewModel::class.java)

        viewModel.deleteData(binding.watchCode.text.toString())
    }
}


