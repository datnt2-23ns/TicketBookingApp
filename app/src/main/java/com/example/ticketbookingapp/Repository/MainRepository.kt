package com.example.ticketbookingapp.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ticketbookingapp.Domain.FlightModel
import com.example.ticketbookingapp.Domain.LocationModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class MainRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadLocation(): LiveData<MutableList<LocationModel>> {
        val listData = MutableLiveData<MutableList<LocationModel>>()
        val ref = firebaseDatabase.getReference("Locations")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<LocationModel>()
                if (snapshot.exists()) {
                    for (childSnapshot in snapshot.children) {
                        val item = childSnapshot.getValue(LocationModel::class.java)
                        item?.let {
                            list.add(it)
                        } ?: run {
                            println("Failed to parse LocationModel: ${childSnapshot.key}")
                        }
                    }
                    println("Locations loaded: ${list.size} items")
                    listData.value = list
                } else {
                    println("Locations node is empty or does not exist")
                    listData.value = mutableListOf() // Trả về rỗng để ngừng loading
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Firebase error in loadLocation: ${error.message}")
                listData.value = mutableListOf() // Trả về rỗng để ngừng loading
            }
        })
        return listData
    }

    fun loadFiltered(from: String, to: String): LiveData<MutableList<FlightModel>> {
        val listData = MutableLiveData<MutableList<FlightModel>>()
        val ref = firebaseDatabase.getReference("Flights")
        val query: Query = ref.orderByChild("from").equalTo(from)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<FlightModel>()
                if (snapshot.exists()) {
                    for (childSnapshot in snapshot.children) {
                        val list = childSnapshot.getValue(FlightModel::class.java)
                        if (list != null && list.To == to) {
                            lists.add(list)
                        }
                    }
                    println("Filtered flights loaded: ${lists.size} items")
                } else {
                    println("Flights node is empty or no matches for from=$from")
                }
                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                println("Firebase error in loadFiltered: ${error.message}")
                listData.value = mutableListOf() // Trả về rỗng để ngừng loading
            }
        })
        return listData
    }
}