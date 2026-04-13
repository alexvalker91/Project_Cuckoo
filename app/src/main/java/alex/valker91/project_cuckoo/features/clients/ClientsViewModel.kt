package alex.valker91.project_cuckoo.features.clients

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import alex.valker91.project_cuckoo.core.Result
import alex.valker91.project_cuckoo.util.UserPrefs
import android.util.Log
import kotlinx.coroutines.withContext

@HiltViewModel
class ClientsViewModel @Inject constructor(
    private val getListOfClientsUseCase: GetListOfClientsUseCase,
    private val userPrefs: UserPrefs
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<ClientScreenState> =
        MutableStateFlow(ClientScreenState(isLoading = true))
    val stateFlow: Flow<ClientScreenState>
        get() = _stateFlow

    init {
        getListOfClients()
    }

    private fun getListOfClients() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2_000)
            val result: Result<List<ClientApi>> = getListOfClientsUseCase.execute(userPrefs.getName())
            Log.d("Kurami", "Result: $result")
            withContext(Dispatchers.Main) {
                when (result) {
                    is Result.Success -> {
                        Log.d("Kurami", "Result: ${result.data.size}")
                        _stateFlow.value =
                            _stateFlow.value.copy(listOfClients = result.data, isLoading = false, error = null)
                    }
                    is Result.Error -> {
                        _stateFlow.value =
                            _stateFlow.value.copy(listOfClients = emptyList(), isLoading = false, error = result.error)
                    }
                    is Result.Loading -> {
                        _stateFlow.value =
                            _stateFlow.value.copy(listOfClients = emptyList(), isLoading = true, error = null)
                    }
                }
            }
        }
    }
}