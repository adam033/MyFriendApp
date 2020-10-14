package com.example.android.myfriendapp

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface MyFriendDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun tambahTeman(friend: MyFriend)

    @Query("SELECT * FROM MyFriend")
    fun ambilSemuaTeman() : LiveData<List<MyFriend>>
}