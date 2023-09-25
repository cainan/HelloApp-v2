package br.com.alura.helloapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val userId: String = "",
    val password: String = "",

    @ColumnInfo(defaultValue = "")
    val userName : String = ""
)