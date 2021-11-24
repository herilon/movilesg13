package com.example.appgrupo13.room_database

import androidx.room.*

@Entity
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val time: String,
    val place: String
)