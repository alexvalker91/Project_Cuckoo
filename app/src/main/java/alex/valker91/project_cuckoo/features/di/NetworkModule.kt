package alex.valker91.project_cuckoo.features.di

import alex.valker91.project_cuckoo.features.accounts.AccountsApiService
import alex.valker91.project_cuckoo.features.clients.ClientsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val URL_BASE = "http://10.68.84.61:8080/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideClientsApiService(retrofit: Retrofit): ClientsApiService =
        retrofit.create(ClientsApiService::class.java)

    @Provides
    @Singleton
    fun provideAccountsApiService(retrofit: Retrofit): AccountsApiService =
        retrofit.create(AccountsApiService::class.java)
}