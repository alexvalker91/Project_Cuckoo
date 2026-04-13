package alex.valker91.project_cuckoo.features.createnewclient

import alex.valker91.project_cuckoo.core.Result
import alex.valker91.project_cuckoo.features.clients.ClientApi
import alex.valker91.project_cuckoo.util.UserPrefs
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CreateNewClientViewModel @Inject constructor(
    private val userPrefs: UserPrefs,
    private val createNewClientUseCase: CreateNewClientUseCase
) : ViewModel() {

    fun handleIntent(event: CreateNewClientEvent) {
        when (event) {
            is CreateNewClient -> createNewClient()
        }
    }

    private val _stateFlow: MutableStateFlow<CreateNewClientScreenState> =
        MutableStateFlow(CreateNewClientScreenState(isLoading = true))
    val stateFlow: Flow<CreateNewClientScreenState>
        get() = _stateFlow

    private val _effect = MutableSharedFlow<CreateClientEffect>()
    val effect: SharedFlow<CreateClientEffect> = _effect.asSharedFlow()

    init {
        fillIn()
    }

    private fun fillIn() {
        _stateFlow.value =
            _stateFlow.value.copy(name = userPrefs.getName(), surname = userPrefs.getSurname(), isLoading = false, error = null)
    }

    private fun createNewClient() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1_000)
            val result: Result<ClientApi> = createNewClientUseCase.execute(userPrefs.getName(), userPrefs.getSurname())
            Log.d("dfasffsfasdf","$result")
            withContext(Dispatchers.Main) {
                when (result) {
                    is Result.Success -> {
                        _stateFlow.value =
                            _stateFlow.value.copy(name = "", surname = "", isLoading = false, error = null)
                        _effect.emit(CreateClientEffect.NavigateBackWithSuccess)
                    }
                    is Result.Error -> {
                        _stateFlow.value =
                            _stateFlow.value.copy(name = "", surname = "", isLoading = false, error = result.error)
                    }
                    is Result.Loading -> {
                        _stateFlow.value =
                            _stateFlow.value.copy(name = "", surname = "", isLoading = true, error = null)
                    }
                }
            }
        }
    }
}