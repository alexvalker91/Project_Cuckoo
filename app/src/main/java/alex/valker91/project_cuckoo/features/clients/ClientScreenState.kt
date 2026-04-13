package alex.valker91.project_cuckoo.features.clients

data class ClientScreenState(
    val listOfClients: List<ClientApi> = emptyList(),
    val isLoading: Boolean = false,
    val error: Exception? = null
)