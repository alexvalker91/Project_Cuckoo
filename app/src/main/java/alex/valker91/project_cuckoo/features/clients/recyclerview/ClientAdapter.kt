package alex.valker91.project_cuckoo.features.clients.recyclerview

import alex.valker91.project_cuckoo.databinding.ClientItemBinding
import alex.valker91.project_cuckoo.features.clients.ClientApi
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class ClientAdapter @AssistedInject constructor(
    @Assisted("clientDetailListener") private val clientDetailListener: (
        clientApi: ClientApi
    ) -> Unit
) : ListAdapter<ClientApi, ClientViewHolder>(
    ClientDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val itemViewHolder = ClientItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        val viewHolder = ClientViewHolder(itemViewHolder)
        setItemListener(viewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.apply {
            val current: ClientApi = getItem(position)
            bind(current)
        }
    }

    private fun setItemListener(clientViewHolder: ClientViewHolder) {
        clientViewHolder.itemView.setOnClickListener {
            val position = clientViewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                clientDetailListener.invoke(getItem(position))
            }
        }
    }
}