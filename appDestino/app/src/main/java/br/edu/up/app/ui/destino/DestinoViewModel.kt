package br.edu.up.app.ui.destino

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.app.data.Destino
import br.edu.up.app.data.DestinoRepository
import br.edu.up.app.data.DestinoRepositorySqlite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DestinoViewModel @Inject constructor(
    @Named("firebase") private val firebaseRepository: DestinoRepository,
//    @Named("sqlite") private val sqliteRepository: DestinoRepositorySqlite
    @Named("sqlite") private val sqliteRepository: DestinoRepository
) : ViewModel() {
//class DestinoViewModel
//    @Inject constructor(val repository: DestinoRepository) : ViewModel() {

    var destino: Destino = Destino()

    private var _destinos = MutableStateFlow(listOf<Destino>())
    val destinos: Flow<List<Destino>> = _destinos

    init {
        viewModelScope.launch {
            firebaseRepository.destinos.collect { destinos ->
                _destinos.value = destinos
            }
        }
    }

//    init {
//        viewModelScope.launch {
//            repository.destinos.collect{destinos ->
//                _destinos.value = destinos
//            }
//        }
//    }

    fun novo(){
        this.destino = Destino()
    }

    fun editar(destino: Destino){
        this.destino = destino
    }


//    fun salvar() = viewModelScope.launch {
//        try {
//            // Realize a operação de salvar no SQLite dentro de Dispatchers.IO
//            sqliteRepository.salvar(destino)
//
//        } catch (e: Exception) {
//            // Trate ou registre a exceção, se necessário
//            e.printStackTrace()
//        }
//    }


//    fun salvar() = viewModelScope.launch {
//        // Aqui você escolhe entre o repositório Firebase ou SQLite com base em suas necessidades
//        try
//        {
//            sqliteRepository.salvar(destino)
//        }
//        catch (e: Exception)
//        {
////            val context = this@DestinoViewModel
////            Toast.makeText(this@MainActivity, "Não foi possível salvar o destino", Toast.LENGTH_SHORT).show()
//        }
//    }



    fun salvar() = viewModelScope.launch {

        if (usarFirebase()) {
            firebaseRepository.salvar(destino)
        } else {
            sqliteRepository.salvar(destino)
        }
    }


//    fun salvar() = viewModelScope.launch {
//        repository.salvar(destino)
//    }


    fun excluir(destino: Destino) = viewModelScope.launch {
        // Aqui você escolhe entre o repositório Firebase ou SQLite com base em suas necessidades
        if (usarFirebase()) {
            firebaseRepository.excluir(destino)
        } else {
            sqliteRepository.excluir(destino)
        }
    }

//    fun excluir(destino: Destino) = viewModelScope.launch {
//        repository.excluir(destino)
//    }

    private fun usarFirebase(): Boolean {
        // Implemente a lógica para decidir se deve usar o Firebase ou não (por exemplo, com base na conectividade)

//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
//        return networkInfo != null && networkInfo.isConnected

        return true
//          return false
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            val isConnected = capabilities != null &&
                    (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))

            if (isConnected) {
                Toast.makeText(context, "Conectado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Não conectado", Toast.LENGTH_SHORT).show()
            }

            return isConnected
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            val isConnected = networkInfo != null && networkInfo.isConnected

            if (isConnected) {
                Toast.makeText(context, "Conectado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Não conectado", Toast.LENGTH_SHORT).show()
            }

            return isConnected
        }
    }

}