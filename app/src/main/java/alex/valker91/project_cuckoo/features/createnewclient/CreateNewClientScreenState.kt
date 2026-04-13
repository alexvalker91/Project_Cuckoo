package alex.valker91.project_cuckoo.features.createnewclient

data class CreateNewClientScreenState(
    val name: String = "",
    val surname: String = "",
    val isLoading: Boolean = false,
    val error: Exception? = null
)