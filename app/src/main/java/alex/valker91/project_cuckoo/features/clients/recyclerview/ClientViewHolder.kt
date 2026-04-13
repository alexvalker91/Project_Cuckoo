package alex.valker91.project_cuckoo.features.clients.recyclerview

import alex.valker91.project_cuckoo.databinding.ClientItemBinding
import alex.valker91.project_cuckoo.features.clients.ClientApi
import androidx.recyclerview.widget.RecyclerView

class ClientViewHolder(val binding: ClientItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clientApi: ClientApi) {
        binding.tvName.text = clientApi.name
        binding.tvSurname.text = clientApi.surname
        binding.tvId.text = clientApi.id.toString()
    }
}