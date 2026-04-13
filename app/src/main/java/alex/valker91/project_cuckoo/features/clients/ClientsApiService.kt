package alex.valker91.project_cuckoo.features.clients

import retrofit2.http.GET
import retrofit2.http.Query

interface ClientsApiService {

    @GET("/api/clients")
    suspend fun getListOfClients(
        @Query("name") name: String
    ): List<ClientApi>
}