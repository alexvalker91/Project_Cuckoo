package alex.valker91.project_cuckoo.features.createnewclient

import alex.valker91.project_cuckoo.core.Result
import alex.valker91.project_cuckoo.features.clients.ClientApi
import alex.valker91.project_cuckoo.features.clients.ClientsNetworkDataSource
import javax.inject.Inject

class CreateNewClientUseCase @Inject constructor(
    private val clientsNetworkDataSource: ClientsNetworkDataSource
) {

    val active: Boolean = true

    suspend fun execute(name: String, surname: String): Result<ClientApi> {

        val requestBody = CreateClientRequest(
            name = name,
            surname = surname,
            active = true
        )
        return clientsNetworkDataSource.createNewClient(requestBody)
    }
}