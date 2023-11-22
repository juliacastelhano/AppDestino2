package br.edu.up.app.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DestinoDAO {

    @Query("select * from destinos")
    fun listar(): Flow<List<Destino>>

    @Insert
    suspend fun inserir(destino: Destino)

    @Update
    suspend fun atualizar(destino: Destino)

    @Delete
    suspend fun excluir(destino: Destino)

    @Query("delete from destinos where id = :id")
    suspend fun excluir(id: Int)

    @Query("delete from destinos")
    suspend fun excluirTodos()
}