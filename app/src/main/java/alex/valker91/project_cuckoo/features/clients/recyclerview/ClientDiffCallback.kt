package alex.valker91.project_cuckoo.features.clients.recyclerview

import alex.valker91.project_cuckoo.features.clients.ClientApi
import androidx.recyclerview.widget.DiffUtil

class ClientDiffCallback : DiffUtil.ItemCallback<ClientApi>() {

    override fun areItemsTheSame(oldItem: ClientApi, newItem: ClientApi): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: ClientApi, newItem: ClientApi): Boolean {
        return oldItem == newItem
    }
}