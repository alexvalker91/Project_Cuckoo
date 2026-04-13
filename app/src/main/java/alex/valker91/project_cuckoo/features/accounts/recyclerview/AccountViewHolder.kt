package alex.valker91.project_cuckoo.features.accounts.recyclerview

import alex.valker91.project_cuckoo.databinding.AccountItemBinding
import alex.valker91.project_cuckoo.features.accounts.AccountApi
import androidx.recyclerview.widget.RecyclerView

class AccountViewHolder(val binding: AccountItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(accountApi: AccountApi) {
        binding.tvClientId.text = accountApi.clientId.toString()
        binding.tvAccountNumber.text = accountApi.accountNumber
        binding.tvId.text = accountApi.id.toString()
    }
}