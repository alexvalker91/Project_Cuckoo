package alex.valker91.project_cuckoo.features.clients

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ClientApi(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "surname") val surname: String,
    @Json(name = "active") val active: Boolean
): Parcelable