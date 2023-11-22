package br.edu.up.app.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


interface DestinoRepository {

    val destinos: Flow<List<Destino>>
    suspend fun salvar(destino: Destino)
    suspend fun excluir(destino: Destino)
    suspend fun excluirTodos()

}





//class DestinoRepository
//    @Inject constructor(val destinoDAO: DestinoDAO) {
//
//    val destinos: Flow<List<Destino>> get() = destinoDAO.listar()
//
//    suspend fun salvar(destino: Destino) {
//        if (destino.id == 0){
//            destinoDAO.inserir(destino)
//        } else {
//            destinoDAO.atualizar(destino)
//        }
//    }
//    suspend fun excluir(destino: Destino){
//        destinoDAO.excluir(destino)
//    }
//
//    suspend fun excluirTodos(){
//        destinoDAO.excluirTodos()
//    }
//
//    init {
//        CoroutineScope(Job()).launch {
//
//            destinoDAO.excluirTodos()
//            val destinos = destinos()
//            for(emp in destinos){
//                emp.id = 0
//                destinoDAO.inserir(emp)
//            }
//        }
//    }
//
//    companion object {
//        fun destinos(): MutableList<Destino> {
//            val listaDestinos = mutableListOf(
//                Destino(
//                    1,
//                    "Punta Cana",
//                    "República Dominicana",
//                    "Bavaro Beach",
//                    "2024",
//                    "bavaro_beach.jpg"
//                )
//            )
//            return listaDestinos
//        }
//    }
//}