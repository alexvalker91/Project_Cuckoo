package alex.valker91.project_cuckoo.util

import android.content.Context

class UserPrefs(context: Context) {

    private val prefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUser(name: String, surname: String) {
        prefs.edit()
            .putString("name", name)
            .putString("surname", surname)
            .putBoolean("is_user_saved", true)
            .apply()
    }

    fun isUserSaved(): Boolean {
        return prefs.getBoolean("is_user_saved", false)
    }

    fun getName(): String {
        return prefs.getString("name", "") ?: ""
    }

    fun getSurname(): String {
        return prefs.getString("surname", "") ?: ""
    }

    fun clear() {
        prefs.edit().clear().apply()
    }
}