package br.edu.up.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "destinos")
data class Destino(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var docId: String,
    var nome: String,
    var pais: String = String(),
    var pontosTuristicos: String = String(),
    var previsaoPartida: String = String(),
    var foto: String = "semfoto.jpg",
) {
    constructor() : this(0,"","","","","", "semfoto.jpg")
}