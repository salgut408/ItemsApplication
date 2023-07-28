package com.salvador.myapplication.di

import android.content.Context
import androidx.room.Room
import com.google.gson.*
import com.salvador.myapplication.API_URL
import com.salvador.myapplication.data.ItemRepositoryImpl
import com.salvador.myapplication.data.api.ApiService
import com.salvador.myapplication.data.data_models.ItemModel
import com.salvador.myapplication.data.database.ItemDao
import com.salvador.myapplication.data.database.ItemDatabase
import com.salvador.myapplication.domain.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideOkhttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .followRedirects(true)
            .hostnameVerifier(object : HostnameVerifier {
                override fun verify(p0: String?, p1: SSLSession?): Boolean {
                    return true
                }
            })
            .build()

    @Singleton
    @Provides
    fun provideApi(
        okHttpClient: OkHttpClient,
    ): ApiService =
        Retrofit.Builder()
            .baseUrl(API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder()
                    .registerTypeAdapter(ItemModel::class.java, NetworkItemAdapter())
                    .create()
            ))
            .build()
            .create(ApiService::class.java)

    @Provides
    fun provideItemRepository(
        apiService: ApiService,
        dao: ItemDao,
    ): ItemRepository = ItemRepositoryImpl(apiService, dao)

    @Provides
    fun provideItemDatabase(@ApplicationContext appContext: Context): ItemDatabase =
        Room.databaseBuilder(
            appContext,
            ItemDatabase::class.java,
            "itemDatabase"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideItemDao(itemDatabase: ItemDatabase): ItemDao = itemDatabase.getItemDao()
}

class NetworkItemAdapter : JsonDeserializer<ItemModel> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): ItemModel {
        val originalJsonObject = json?.asJsonObject
        val replacementJsonObject = JsonObject()
        originalJsonObject?.entrySet()?.forEach { (key, value) ->
            val objKey = key.lowercase(Locale.ROOT)
            replacementJsonObject.add(objKey, value)
        }
        return Gson().fromJson(replacementJsonObject, ItemModel::class.java)
    }
}




















