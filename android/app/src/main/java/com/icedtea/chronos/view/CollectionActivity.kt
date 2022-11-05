package com.icedtea.chronos.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.icedtea.chronos.R
import com.icedtea.chronos.databinding.ActivityAddFormBinding
import com.icedtea.chronos.databinding.ActivityCollectionBinding
import com.icedtea.chronos.viewmodel.RecyclerCollectionAdapter
import com.icedtea.chronos.model.WatchDataClass
import com.icedtea.chronos.viewmodel.CollectionViewModel
import kotlinx.android.synthetic.main.activity_collection.*
class CollectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCollectionBinding
    private lateinit var watchList: ArrayList<WatchDataClass>
    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCollectionBinding.inflate(layoutInflater) // view binding
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        watchList = arrayListOf<WatchDataClass>()

        // Reading Data from Firebase
        Log.i("DATABASE", "Calling Read")
        readData()
    }

    // Reads Data from Database --> Takes userEmail and Database Reference
    private fun readData(){
        // Reference to Firebase Database
        database = FirebaseDatabase.getInstance("https://chronos-watch-data-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("user")
        Log.i("DATABASE", "$database")
        // Get ViewModel Reference
        val viewModel = ViewModelProvider(this).get(CollectionViewModel::class.java)
        database.addValueEventListener(object: ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){

                    for(watchSnapshot in snapshot.children){
                        val watch = watchSnapshot.getValue(WatchDataClass::class.java)
                        Log.i("DATABASE", "$watch")
                        watchList.add(watch!!)
                    }
                    // Recycler View
                    recycler_view.adapter = RecyclerCollectionAdapter(watchList) {
                        showDetail(it)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not Yet Implemented")
            }
        })
    }

    // Options Menu for Adding
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_menu,menu)
        return true
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                openAddWatchForm()
            }
        }
        return true
    }
    // Shoe Details of a Watch
    private fun showDetail(record : WatchDataClass){
        Intent(this, WatchDetailActivity::class.java).apply{
            putExtra("watchSelected", record)
            startActivity(this)
        }
    }
    // Redirects to add watch activity
    private fun openAddWatchForm(){
        Intent(this, AddFormActivity::class.java).apply {
            startActivity(this)
        }
    }

}




