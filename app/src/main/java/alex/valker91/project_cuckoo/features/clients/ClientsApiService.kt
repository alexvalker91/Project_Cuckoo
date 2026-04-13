package alex.valker91.project_cuckoo.features.clients

import alex.valker91.project_cuckoo.features.createnewclient.CreateClientRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ClientsApiService {

    @GET("/api/clients")
    suspend fun getListOfClients(
        @Query("name") name: String
    ): List<ClientApi>

    @POST("/api/clients")
    suspend fun createNewClient(
        @Body request: CreateClientRequest
    ): ClientApi
}