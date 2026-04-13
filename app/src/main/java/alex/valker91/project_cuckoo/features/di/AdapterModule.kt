package alex.valker91.project_cuckoo.features.di

import alex.valker91.project_cuckoo.features.clients.ClientApi
import alex.valker91.project_cuckoo.features.clients.recyclerview.ClientAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface AdapterModule {

    fun createClientAdapter(
        @Assisted("clientDetailListener") clientDetailListener: (clientApi: ClientApi) -> Unit
    ): ClientAdapter
}