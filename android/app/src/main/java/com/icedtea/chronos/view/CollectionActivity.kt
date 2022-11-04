package com.icedtea.chronos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.icedtea.chronos.databinding.ActivityAddFormBinding
import com.icedtea.chronos.databinding.ActivityCollectionBinding
import com.icedtea.chronos.viewmodel.RecyclerCollectionAdapter
import com.icedtea.chronos.model.WatchDataClass
import kotlinx.android.synthetic.main.activity_collection.*
class CollectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCollectionBinding
    private lateinit var watchList: ArrayList<WatchDataClass>
    lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCollectionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        watchList = arrayListOf<WatchDataClass>()
        Log.i("DATABASE", "Calling Read")
        readData()
    }

    // Reads Data from Database --> Takes userEmail and Database Reference
    private fun readData(){
        // Reference to Firebase Database
        database = FirebaseDatabase.getInstance("https://chronos-watch-data-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("user")
        Log.i("DATABASE", "$database")
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
                        Log.i("RECYCLER", "Click on RecyclerViewItem")
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not Yet Implemented")
            }
        })
    }
}


