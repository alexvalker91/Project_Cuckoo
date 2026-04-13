package alex.valker91.project_cuckoo.features.accounts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alex.valker91.project_cuckoo.databinding.FragmentAccountsBinding
import alex.valker91.project_cuckoo.features.accounts.recyclerview.AccountAdapter
import alex.valker91.project_cuckoo.features.di.AccountAdapterModule
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class AccountsFragment : Fragment() {

    private val viewModel: AccountsViewModel by viewModels()
    private var _binding: FragmentAccountsBinding? = null
    private val binding get() = _binding!!

    private val args: AccountsFragmentArgs by navArgs()

    @Inject
    lateinit var customAdapterFactory: AccountAdapterModule
    private val accountDetailsListener: (
        accountApi: AccountApi
    ) -> Unit = {
            accountApi ->
        val action = AccountsFragmentDirections.actionAccountsFragmentToBalancesFragment(accountApi)
        findNavController().navigate(action)
    }

    private lateinit var accountAdapter: AccountAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerFlow()
        setUpRecyclerView()
    }

    private fun observerFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { result ->
                    accountAdapter.submitList(result.listOfAccounts)
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        accountAdapter = customAdapterFactory.createAccountAdapter(accountDetailsListener)
        recyclerView = binding.recyclerView
        recyclerView.apply {
            adapter = accountAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}