package com.icedtea.chronos.viewmodel

import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.icedtea.chronos.model.WatchDataClass
import com.icedtea.chronos.view.AddFormActivity
import com.icedtea.chronos.view.CollectionActivity
import kotlin.random.Random

class FormViewModel : ViewModel() {

    lateinit var database : DatabaseReference

    fun addWatchData(data : WatchDataClass, userEmail: String){
        database = FirebaseDatabase.getInstance("https://chronos-watch-data-default-rtdb.asia-southeast1.firebasedatabase.app").getReference()
        database.child("user").child(data.watchCode).setValue(data).addOnSuccessListener {
            Log.i("DATABASE", "Check Database")
        }.addOnFailureListener{
            Log.i("DATABASE", "$it")
        }
    }
}