package br.edu.up.app.data

import br.edu.up.app.R

class Fotos{
    companion object {
        fun get(key: String): Int {
            val mapOfFotos = mapOf(
                "bavaro_beach.jpg" to R.drawable.bavaro_beach,
                "anse_imagem_ponto.jpg" to R.drawable.anse_imagem_ponto,
                "anse_imagem2.jpg" to R.drawable.anse_imagem2,
                "anse_imagem3.jpg" to R.drawable.anse_imagem3,
                "anse_source_d_argent.jpg" to R.drawable.anse_source_d_argent,
                "aspen.jpg" to R.drawable.aspen,
                "aspen_imagem_ponto.jpg" to R.drawable.aspen_imagem_ponto,
                "aspen_imagem1.jpg" to R.drawable.aspen_imagem1,
                "aspen_imagem2.jpg" to R.drawable.aspen_imagem2,
                "aspen_imagem3.jpg" to R.drawable.aspen_imagem3,
                "fernando_noronha.jpg" to R.drawable.fernando_noronha,
                "ilhas_imagem_ponto.jpg" to R.drawable.ilhas_imagem_ponto,
                "ilhas_imagem3.jpg" to R.drawable.ilhas_imagem3,
                "ilhas_phi_phi.jpg" to R.drawable.ilhas_phi_phi,
                "noronha_imagem_ponto.jpg" to R.drawable.noronha_imagem_ponto,
                "noronha_imagem1.jpg" to R.drawable.noronha_imagem1,
                "noronha_imagem2.jpg" to R.drawable.noronha_imagem2,
                "noronha_imagem3.jpg" to R.drawable.noronha_imagem3,
                "parque_banff.jpg" to R.drawable.parque_banff,
                "parque_imagem_ponto.jpg" to R.drawable.parque_imagem_ponto,
                "parque_imagem1.jpg" to R.drawable.parque_imagem1,
                "parque_imagem2.jpg" to R.drawable.parque_imagem2,
                "parque_imagem3.jpg" to R.drawable.parque_imagem3,
                "tulum.png" to R.drawable.tulum,
                "tulum_imagem_ponto.jpg" to R.drawable.tulum_imagem_ponto,
                "tulum_imagem1.jpg" to R.drawable.tulum_imagem1,
                "tulum_imagem2.jpg" to R.drawable.tulum_imagem2

            )
            return mapOfFotos[key] ?: R.drawable.semfoto
        }
    }
}