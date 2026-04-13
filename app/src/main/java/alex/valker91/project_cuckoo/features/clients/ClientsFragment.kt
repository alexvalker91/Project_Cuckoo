package alex.valker91.project_cuckoo.features.clients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alex.valker91.project_cuckoo.databinding.FragmentClientsBinding
import alex.valker91.project_cuckoo.features.clients.recyclerview.ClientAdapter
import alex.valker91.project_cuckoo.features.di.AdapterModule
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ClientsFragment : Fragment() {

    private val viewModel: ClientsViewModel by viewModels()

    private var _binding: FragmentClientsBinding? = null
    private val binding get() = _binding!!


    @Inject
    lateinit var customAdapterFactory: AdapterModule
    private val clientDetailsListener: (
        clientApi: ClientApi
    ) -> Unit = {
            clientApi ->
        val action = ClientsFragmentDirections.actionClientsFragmentToAccountsFragment(clientApi)
        findNavController().navigate(action)
    }

    private lateinit var clientAdapter: ClientAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClientsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerFlow()
        setUpRecyclerView()
        observerButton()
    }

    private fun observerButton() {
        binding.button.setOnClickListener {
            val action = ClientsFragmentDirections.actionClientsFragmentToCreateNewClientFragment()
            findNavController().navigate(action)
        }
    }
    private fun observerFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { result ->
                    clientAdapter.submitList(result.listOfClients)
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        clientAdapter = customAdapterFactory.createClientAdapter(clientDetailsListener)
        recyclerView = binding.recyclerView
        recyclerView.apply {
            adapter = clientAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}