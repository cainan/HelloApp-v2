package br.com.alura.helloapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.alura.helloapp.data.Contato
import br.com.alura.helloapp.data.User
import br.com.alura.helloapp.database.converters.*

@Database(entities = [Contato::class, User::class], version = 2, exportSchema = true)
@TypeConverters(Converters::class)
abstract class HelloAppDatabase : RoomDatabase() {
    abstract fun contatoDao(): ContatoDao

    abstract fun userDao(): UserDao
}