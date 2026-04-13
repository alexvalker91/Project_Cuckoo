package alex.valker91.project_cuckoo.core

sealed class Result<out T> {

    data class Success<T>(val data: T) : Result<T>()
    data class Error(val error: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}