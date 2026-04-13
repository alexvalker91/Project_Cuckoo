package alex.valker91.project_cuckoo.features.accounts

import alex.valker91.project_cuckoo.core.Result
import javax.inject.Inject

class AccountsNetworkDataSource @Inject constructor(
    private val accountsApiService: AccountsApiService
) {

    suspend fun getListOfAccounts(name: String): Result<List<AccountApi>> {
        return try {
            val response = accountsApiService.getListOfAccounts(name)
            if (response.isSuccessful) {
                val listOfAccounts = response.body() ?: emptyList()
                Result.Success(listOfAccounts)
            } else {
                Result.Error(Exception("Error server: ${response.code()}"))
            }
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }
}