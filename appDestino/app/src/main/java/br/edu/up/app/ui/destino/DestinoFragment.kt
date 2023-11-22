package br.edu.up.app.ui.destino

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.InputEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.edu.up.app.R
import br.edu.up.app.data.Destino
import br.edu.up.app.databinding.FragmentDestinoBinding
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DestinoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel : DestinoViewModel by activityViewModels()
        val binding = FragmentDestinoBinding.inflate(layoutInflater)


        var tituloItem : String


        binding.btnFoto.setOnClickListener {
            PopupMenu(requireContext(), binding.btnFoto).apply {
                menuInflater.inflate(R.menu.menu_fotos, this.menu)
                setOnMenuItemClickListener { item ->
                    tituloItem = item.title.toString()

                    binding.btnFoto.text = SpannableStringBuilder(tituloItem)

                    Toast.makeText(requireContext(), "$tituloItem", Toast.LENGTH_SHORT).show()
                    true
                }
                show()
            }
        }

        val destino = viewModel.destino
        binding.inputNome.setText(destino.nome)
        binding.inputPais.setText(destino.pais)
        binding.inputPontosTuristicos.setText(destino.pontosTuristicos)
        binding.inputPrevisaoPartida.setText(destino.previsaoPartida)
        binding.btnFoto.setText(destino.foto)

        binding.btnSalvar.setOnClickListener {
            val destinoSalvar = Destino(
                destino.id,
                destino.docId,
                binding.inputNome.text.toString(),
                binding.inputPais.text.toString(),
                binding.inputPontosTuristicos.text.toString(),
                binding.inputPrevisaoPartida.text.toString(),
                binding.btnFoto.text.toString(),
            )
            viewModel.destino = destinoSalvar
            viewModel.salvar()
            findNavController().popBackStack()
        }

        return binding.root
    }
}