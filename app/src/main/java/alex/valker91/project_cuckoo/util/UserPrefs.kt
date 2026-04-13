package alex.valker91.project_cuckoo.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPrefs @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUser(name: String, surname: String) {
        prefs.edit()
            .putString("name", name)
            .putString("surname", surname)
            .putBoolean("is_user_saved", true)
            .apply()
    }

    fun getName(): String {
        return prefs.getString("name", "") ?: ""
    }

    fun getSurname(): String {
        return prefs.getString("surname", "") ?: ""
    }

    fun getFullName(): String {
        return "${getName()} ${getSurname()}".trim()
    }

    fun isUserSaved(): Boolean {
        return prefs.getBoolean("is_user_saved", false)
    }
}