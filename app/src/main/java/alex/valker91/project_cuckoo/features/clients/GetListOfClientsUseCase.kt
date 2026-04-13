package alex.valker91.project_cuckoo.features.clients

import alex.valker91.project_cuckoo.core.Result
import javax.inject.Inject

class GetListOfClientsUseCase @Inject constructor(
    private val clientsNetworkDataSource: ClientsNetworkDataSource
) {

    suspend fun execute(name: String): Result<List<ClientApi>> {
        return clientsNetworkDataSource.getListOfClients(name)
    }
}