package alex.valker91.project_cuckoo.features.di

import alex.valker91.project_cuckoo.features.accounts.AccountApi
import alex.valker91.project_cuckoo.features.accounts.recyclerview.AccountAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface AccountAdapterModule {

    fun createAccountAdapter(
        @Assisted("accountDetailListener") accountDetailListener: (accountApi: AccountApi) -> Unit
    ): AccountAdapter
}