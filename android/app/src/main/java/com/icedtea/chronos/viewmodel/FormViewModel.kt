package com.icedtea.chronos.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.icedtea.chronos.model.WatchDataClass

class FormViewModel : ViewModel() {

    lateinit var database : DatabaseReference

    fun addWatchData(data : WatchDataClass){
        database = FirebaseDatabase.getInstance().getReference("https://chronos-watch-data-default-rtdb.asia-southeast1.firebasedatabase.app/")
        database.child("watch").setValue(data).addOnSuccessListener {
            Log.i("DATABASE", "Check Database")
        }
    }
}