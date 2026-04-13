package alex.valker91.project_cuckoo.features.accounts

import alex.valker91.project_cuckoo.core.Result
import javax.inject.Inject

class GetListOfAccountsUseCase @Inject constructor(
    private val accountsNetworkDataSource: AccountsNetworkDataSource
) {

    suspend fun execute(name: String): Result<List<AccountApi>> {
        return accountsNetworkDataSource.getListOfAccounts(name)
    }
}