package com.icedtea.chronos.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.icedtea.chronos.databinding.ActivityAddFormBinding
import com.icedtea.chronos.viewmodel.RecyclerCollectionAdapter
import com.icedtea.chronos.model.WatchDataClass
import kotlinx.android.synthetic.main.activity_collection.*


class CollectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddFormBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var watchList = fillData()
        recycler_view.adapter = RecyclerCollectionAdapter(watchList){
            Log.i("RECYCLER", "Click on RecyclerViewItem")
            }
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        }
    }
    fun fillData():List<WatchDataClass>{
        val watchList = ArrayList<WatchDataClass>()
        for (i in 1..100){
            val watchName = "Rolex Daytona"
            val watchPrice = "1034"
            val watchDial = "120"
            val watchDes = "White Gold Plated"
            val watchColor = "Midnight Blue"
            var watch = WatchDataClass(watchName,watchDes,watchDial,watchColor,watchPrice)
            watchList+=watch
        }
        return watchList
    }
