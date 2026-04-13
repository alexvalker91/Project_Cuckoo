package alex.valker91.project_cuckoo.features.createnewclient

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateClientRequest(
    @Json(name = "name") val name: String,
    @Json(name = "surname") val surname: String,
    @Json(name = "active") val active: Boolean
)