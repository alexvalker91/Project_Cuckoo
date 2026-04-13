package alex.valker91.project_cuckoo.features.accounts

data class AccountScreenState(
    val listOfAccounts: List<AccountApi> = emptyList(),
    val isLoading: Boolean = false,
    val error: Exception? = null
)