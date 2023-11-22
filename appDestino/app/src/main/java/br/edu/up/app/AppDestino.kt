package br.edu.up.app

import android.app.Application
import android.content.Context
import br.edu.up.app.data.BancoSQLite
import br.edu.up.app.data.DestinoDAO
import br.edu.up.app.data.DestinoRepository
import br.edu.up.app.data.DestinoRepositoryFirebase
import br.edu.up.app.data.DestinoRepositorySqlite
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.firestore.ktx.memoryCacheSettings
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@HiltAndroidApp
@InstallIn(SingletonComponent::class)
class AppDestino : Application() {

//    @Provides
////    @Named("firebase")
//    fun provideDestinosRef() : CollectionReference {
//        return Firebase.firestore.collection("destinos")
//    }

    @Provides
    fun provideDestinosRef() : CollectionReference {
        val firestore = Firebase.firestore
        val settings = firestoreSettings {
            setLocalCacheSettings(memoryCacheSettings {  })
        }
        firestore.firestoreSettings = settings
        return firestore.collection("destinos")
    }


    @Provides
    @Named("firebase")
    fun provideDestinoRepositoryFirebase(destinosRef: CollectionReference)
            : DestinoRepository{
        return DestinoRepositoryFirebase(destinosRef)
    }

    @Provides
    @Named("sqlite")
    fun provideDestinoRepositorySqlite(destinoDAO: DestinoDAO): DestinoRepository {
        return DestinoRepositorySqlite(destinoDAO)
    }

//    @Provides
//    @Named("sqlite")
//    fun provideDestinoRepositorySqlite(destinoDAO: DestinoDAO): DestinoRepositorySqlite {
//        return DestinoRepositorySqlite(destinoDAO)
//    }



//    @Provides
//    fun provideDestinoRepository(destinoDAO: DestinoDAO) : DestinoRepository {
//        return DestinoRepository(destinoDAO)
//    }
//
//
    @Provides
    fun provideDestinoDAO(bancoSQLite: BancoSQLite): DestinoDAO {
        return bancoSQLite.destinoDAO()
    }
//
    @Provides
    @Singleton
    fun provideBanco(@ApplicationContext ctx: Context): BancoSQLite {
        return BancoSQLite.get(ctx)
    }
}