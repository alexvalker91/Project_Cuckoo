package alex.valker91.project_cuckoo.features.accounts

import alex.valker91.project_cuckoo.core.Result
import alex.valker91.project_cuckoo.features.clients.ClientApi
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val getListOfAccountsUseCase: GetListOfAccountsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<AccountScreenState> =
        MutableStateFlow(AccountScreenState(isLoading = true))
    val stateFlow: Flow<AccountScreenState>
        get() = _stateFlow

    init {
        val clientApi = savedStateHandle.get<ClientApi>("clientApi")
        val clientName = clientApi?.name ?: ""
        val clientSurname = clientApi?.surname ?: ""
        getListOfAccounts(clientName, clientSurname)
    }

    private fun getListOfAccounts(clientName: String, clientSurname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1_000)
            Log.d("Kurami", "Result: ${clientName + "_" + clientSurname}")
            val result: Result<List<AccountApi>> = getListOfAccountsUseCase.execute(clientName + "_" + clientSurname)
            Log.d("Kurami", "Result: $result")
            withContext(Dispatchers.Main) {
                when (result) {
                    is Result.Success -> {
                        Log.d("Kurami", "Result: ${result.data.size}")
                        _stateFlow.value =
                            _stateFlow.value.copy(listOfAccounts = result.data, isLoading = false, error = null)
                    }
                    is Result.Error -> {
                        _stateFlow.value =
                            _stateFlow.value.copy(listOfAccounts = emptyList(), isLoading = false, error = result.error)
                    }
                    is Result.Loading -> {
                        _stateFlow.value =
                            _stateFlow.value.copy(listOfAccounts = emptyList(), isLoading = true, error = null)
                    }
                }
            }
        }
    }
}