package com.example.store.core.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.store.feature.auth.data.AuthRepositoryImpl
import com.example.store.feature.auth.domain.repository.AuthRepository
import com.example.store.feature.profile.data.repository.PurchaseHistoryRepositoryImpl
import com.example.store.feature.profile.domain.repository.PurchaseHistoryRepository
import com.example.store.core.data.local.data_source.StoreDatabase
import com.example.store.core.data.remote.FirebaseDataSource
import com.example.store.core.data.remote.FirebaseDataSourceImpl
import com.example.store.feature.store.data.repository.CartRepositoryImpl
import com.example.store.feature.store.data.repository.PackRepositoryImpl
import com.example.store.core.data.util.PrepopulateData.barcodes
import com.example.store.core.data.util.PrepopulateData.packPrices
import com.example.store.core.data.util.PrepopulateData.packs
import com.example.store.core.data.util.PrepopulateData.units
import com.example.store.core.data.util.ioThread
import com.example.store.feature.store.domain.repository.CartRepository
import com.example.store.feature.store.domain.repository.PackRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreModule {

    @Singleton
    @Provides
    fun provideStoreDatabase(app: Application): StoreDatabase {
        lateinit var instance: StoreDatabase
        val database = Room.databaseBuilder(
            app,
            StoreDatabase::class.java,
            StoreDatabase.DATABASE_NAME
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                ioThread {
                    instance.unitDao().upsertUnits(units)
                    instance.packDao().upsertPacks(packs)
                    instance.packPriceDao().upsertPackPrices(packPrices)
                    instance.barcodeDao().upsertBarcodes(barcodes)
                }
            }
        }).build()
        instance = database
        return database
    }

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Singleton
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Singleton
    @Provides
    fun provideUserDataSource(firestore: FirebaseFirestore): FirebaseDataSource {
        return FirebaseDataSourceImpl(firestore)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        firebaseDataSource: FirebaseDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(auth, firebaseDataSource)
    }

    @Singleton
    @Provides
    fun providePackRepository(db: StoreDatabase): PackRepository {
        return PackRepositoryImpl(db.packDao())
    }

    @Singleton
    @Provides
    fun provideCartRepository(
        firebaseDataSource: FirebaseDataSource,
        firebaseAuth: FirebaseAuth
    ): CartRepository {
        return CartRepositoryImpl(firebaseDataSource, firebaseAuth)
    }

    @Singleton
    @Provides
    fun providePurchaseHistoryRepository(
        firebaseDataSource: FirebaseDataSource,
        firebaseAuth: FirebaseAuth
    ): PurchaseHistoryRepository {
        return PurchaseHistoryRepositoryImpl(firebaseDataSource, firebaseAuth)
    }
}


