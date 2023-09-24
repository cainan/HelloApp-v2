package br.com.alura.helloapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val name: String = "",
    val password: String = "",
)