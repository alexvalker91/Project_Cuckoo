package alex.valker91.project_cuckoo.features.clients

import alex.valker91.project_cuckoo.core.Result
import alex.valker91.project_cuckoo.features.createnewclient.CreateClientRequest
import android.util.Log
import javax.inject.Inject

class ClientsNetworkDataSource @Inject constructor(
    private val clientsApiService: ClientsApiService
) {

    suspend fun getListOfClients(name: String): Result<List<ClientApi>> {
        return try {
            val listOfFilms = clientsApiService.getListOfClients(name)
            Result.Success(listOfFilms)
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

    suspend fun createNewClient(сreateClientRequest: CreateClientRequest): Result<ClientApi> {
        return try {
            val clientApi = clientsApiService.createNewClient(сreateClientRequest)
            Result.Success(clientApi)
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }
}