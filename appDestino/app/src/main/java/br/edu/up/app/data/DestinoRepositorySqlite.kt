package br.edu.up.app.data

import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject

class DestinoRepositorySqlite
    @Inject constructor(val destinoDAO: DestinoDAO) : DestinoRepository {

        override val destinos: Flow<List<Destino>> get() = destinoDAO.listar()
        override suspend fun salvar(destino: Destino) {
            try {
                if (destino.id == 0){
                    destinoDAO.inserir(destino)
                } else {
                    destinoDAO.atualizar(destino)
                }
            }
            catch (e: Exception) {
                e.printStackTrace()
            }


        }
        override suspend fun excluir(destino: Destino){
            destinoDAO.excluir(destino)
        }

        override suspend fun excluirTodos(){
            destinoDAO.excluirTodos()
    }
}