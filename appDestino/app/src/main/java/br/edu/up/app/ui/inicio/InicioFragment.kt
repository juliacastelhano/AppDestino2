package br.edu.up.app.ui.inicio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.up.app.R
import br.edu.up.app.databinding.ActivityMainBinding
import br.edu.up.app.databinding.FragmentInicioBinding
import br.edu.up.app.databinding.FragmentListaDestinosBinding
import br.edu.up.app.ui.destino.DestinoViewModel
import br.edu.up.app.ui.destino.DestinosAdapter
import br.edu.up.app.ui.destino.DestinosFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InicioFragment : Fragment() {

    private lateinit var binding: FragmentInicioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel : DestinoViewModel by activityViewModels()

        binding = FragmentInicioBinding.inflate(layoutInflater)


        binding.btnCadastrar.setOnClickListener { view ->
            viewModel.novo()

            findNavController().navigate(R.id.action_nav_gallery_to_destinoFragment)
        }


        return binding.root
    }

}