package alex.valker91.project_cuckoo.features.accounts.recyclerview

import alex.valker91.project_cuckoo.databinding.AccountItemBinding
import alex.valker91.project_cuckoo.features.accounts.AccountApi
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class AccountAdapter @AssistedInject constructor(
    @Assisted("accountDetailListener") private val accountDetailListener: (
        accountApi: AccountApi
    ) -> Unit
) : ListAdapter<AccountApi, AccountViewHolder>(
    AccountDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val itemViewHolder = AccountItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        val viewHolder = AccountViewHolder(itemViewHolder)
        setItemListener(viewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.apply {
            val current: AccountApi = getItem(position)
            bind(current)
        }
    }

    private fun setItemListener(accountViewHolder: AccountViewHolder) {
        accountViewHolder.itemView.setOnClickListener {
            val position = accountViewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                accountDetailListener.invoke(getItem(position))
            }
        }
    }
}