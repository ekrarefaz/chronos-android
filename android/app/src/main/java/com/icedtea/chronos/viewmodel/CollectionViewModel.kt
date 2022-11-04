package com.icedtea.chronos.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.icedtea.chronos.model.WatchDataClass

class CollectionViewModel : ViewModel() {

    lateinit var watchList: ArrayList<WatchDataClass>

    // Reads Data from Database --> Takes userEmail and Database Reference

}