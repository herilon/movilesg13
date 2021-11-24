package com.example.appgrupo13

data class Task (val id: Int, val task: String, val time: String, val place: String){
    override fun toString(): String {
        return task
    }
}