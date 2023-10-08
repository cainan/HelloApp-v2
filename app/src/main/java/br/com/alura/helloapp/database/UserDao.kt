package br.com.alura.helloapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.alura.helloapp.data.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM User")
    fun searchAll(): Flow<List<User>>

    @Query("SELECT * FROM User WHERE userId = :username")
    fun buscaPorId(username: String): Flow<User?>

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}