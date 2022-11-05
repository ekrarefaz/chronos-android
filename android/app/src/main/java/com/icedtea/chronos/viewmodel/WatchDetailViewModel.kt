package com.icedtea.chronos.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.icedtea.chronos.model.WatchDataClass

class WatchDetailViewModel: ViewModel() {
    // overwrites existing data with any changes
    fun updateDatabase(watch:WatchDataClass,watchCode:String)
    {
        val database = FirebaseDatabase.getInstance("https://chronos-watch-data-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("user")
        database.child(watchCode).setValue(watch).addOnSuccessListener {
            Log.i("DATABASE", "Check Database")
        }.addOnFailureListener{
            Log.i("DATABASE", "$it")
        }
    }
    // removes the selected item from database
    fun deleteData(watchCode: String){
        val database = FirebaseDatabase.getInstance("https://chronos-watch-data-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("user")
        database.child(watchCode).removeValue().addOnSuccessListener {
            Log.i("DATABASE", "Check Database")
        }.addOnFailureListener{
            Log.i("DATABASE", "$it")
        }
    }
}