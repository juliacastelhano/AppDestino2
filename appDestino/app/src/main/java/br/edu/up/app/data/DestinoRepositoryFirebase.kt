package br.edu.up.app.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class DestinoRepositoryFirebase
@Inject constructor(val destinosRef : CollectionReference): DestinoRepository {

    private var _destinos = MutableStateFlow(listOf<Destino>())
    override val destinos: Flow<List<Destino>> get() = _destinos.asStateFlow()

    init {
        destinosRef.addSnapshotListener { snapshot, _ ->
            if (snapshot == null){
                _destinos = MutableStateFlow(listOf())
            } else {
                var destinos = mutableListOf<Destino>()
                snapshot.documents.forEach { doc ->
                    val destino = doc.toObject<Destino>()
                    if (destino != null){
                        destino.docId = doc.id
                        destinos.add(destino)
                    }
                }
                _destinos.value = destinos
            }

        }
    }

    override suspend fun salvar(destino: Destino) {
        if(destino.docId.isNullOrEmpty()){
            var doc = destinosRef.document()
            destino.docId = doc.id
            doc.set(destino)
        } else {
            destinosRef.document(destino.docId).set(destino)
        }
    }

    override suspend fun excluir(destino: Destino) {
        destinosRef.document(destino.docId).delete()
    }

    override suspend fun excluirTodos() {
        _destinos.collect{ destinos ->
            destinos.forEach{ destino ->
                destinosRef.document(destino.docId).delete()
            }
        }
    }
}