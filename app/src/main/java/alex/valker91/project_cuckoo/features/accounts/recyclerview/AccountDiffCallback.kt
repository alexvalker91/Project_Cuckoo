package alex.valker91.project_cuckoo.features.accounts.recyclerview

import alex.valker91.project_cuckoo.features.accounts.AccountApi
import androidx.recyclerview.widget.DiffUtil

class AccountDiffCallback : DiffUtil.ItemCallback<AccountApi>() {

    override fun areItemsTheSame(oldItem: AccountApi, newItem: AccountApi): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: AccountApi, newItem: AccountApi): Boolean {
        return oldItem == newItem
    }
}