package br.edu.up.app.ui.destino

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.edu.up.app.databinding.FragmentListaDestinosBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DestinosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Injeção automática de dependência
        val viewModel : DestinoViewModel by activityViewModels()

        val binding = FragmentListaDestinosBinding.inflate(layoutInflater)
        val recyclerView = binding.root

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.destinos.collect{ destinos ->
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = DestinosAdapter(destinos, viewModel)
                }
            }
        }
        return binding.root
    }
}