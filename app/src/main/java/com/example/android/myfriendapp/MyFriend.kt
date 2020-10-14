package com.example.android.myfriendapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MyFriend(
    @PrimaryKey(autoGenerate = true)
    val temanId: Int? = null,
    val nama: String? = null,
    val kelamin: String? = null,
    val email: String? = null,
    val telp: String? = null,
    val alamat: String? = null,
    )