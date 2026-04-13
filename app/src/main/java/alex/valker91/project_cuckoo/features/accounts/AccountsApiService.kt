package alex.valker91.project_cuckoo.features.accounts

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface AccountsApiService {

    @GET("/api/accounts")
    suspend fun getListOfAccounts(
        @Query("accountNumber") accountNumber: String
    ): Response<List<AccountApi>>
}