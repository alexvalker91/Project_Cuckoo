package alex.valker91.project_cuckoo.features.accounts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
class AccountApi(
    @Json(name = "id") val id: Int,
    @Json(name = "clientId") val clientId: Int,
    @Json(name = "accountNumber") val accountNumber: String,
    @Json(name = "accountActive") val accountActive: Boolean
): Parcelable