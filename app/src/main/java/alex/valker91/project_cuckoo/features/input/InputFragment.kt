package alex.valker91.project_cuckoo.features.input

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import alex.valker91.project_cuckoo.databinding.FragmentInputBinding
import alex.valker91.project_cuckoo.util.UserPrefs
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController

@AndroidEntryPoint
class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerButton()
    }

    private fun observerButton() {
        binding.btnSave.setOnClickListener {
            val name: String = binding.etName.text.toString()
            val surname: String = binding.etSurname.text.toString()
            UserPrefs(requireContext()).saveUser(name, surname)
            val action = InputFragmentDirections.actionInputFragmentToClientsFragment()
            findNavController().navigate(action)
        }
    }
}