package com.asabirov.translation.di

//import com.asabirov.translation.data.database.DatabaseDriverFactory
import android.app.Application
import com.asabirov.translation.data.database.DatabaseDriverFactory
import com.asabirov.translation.data.database.history.SqlDelightHistoryDataSource
import com.asabirov.translation.data.remote.HttpClientFactory
import com.asabirov.translation.data.remote.KtorTranslationClient
import com.asabirov.translation.database.TranslateDatabase
import com.asabirov.translation.domain.Translate
import com.asabirov.translation.domain.TranslationClient
import com.asabirov.translation.domain.history.HistoryDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TranslationModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun provideTranslateClient(httpClient: HttpClient): TranslationClient {
        return KtorTranslationClient(httpClient)
    }

    @Provides
    @Singleton
    fun provideDatabaseDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideHistoryDataSource(driver: SqlDriver): HistoryDataSource {
        return SqlDelightHistoryDataSource(TranslateDatabase(driver))
    }

    @Provides
    @Singleton
    fun provideTranslateUseCase(
        client: TranslationClient,
        dataSource: HistoryDataSource
    ): Translate {
        return Translate(client, dataSource)
    }
}