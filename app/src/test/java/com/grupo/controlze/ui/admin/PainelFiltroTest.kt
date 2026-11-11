package com.grupo.controlze.ui.admin

import com.grupo.controlze.logic.Fase
import com.grupo.controlze.logic.TipoFase
import org.junit.Assert.assertEquals
import org.junit.Test

class PainelFiltroTest {
    private val fases = listOf(
        Fase("criada-1", 2.5, "Teste", TipoFase.MULTIPLA_ESCOLHA, "Criada", original = false),
        Fase("original-01", 1.0, "Teste", TipoFase.MULTIPLA_ESCOLHA, "Original", original = true),
        Fase("original-02", 2.0, "Teste", TipoFase.MULTIPLA_ESCOLHA, "Outra", original = true)
    )

    @Test
    fun ordenaNumeraEFiltraSemAlterarAFaseOriginal() {
        assertEquals(listOf(1, 2, 3), itensDoPainel(fases, FiltroPainel.TODAS).map { it.numero })
        assertEquals(listOf("original-01", "original-02"),
            itensDoPainel(fases, FiltroPainel.ORIGINAIS).map { it.fase.id })
        assertEquals(listOf("criada-1"),
            itensDoPainel(fases, FiltroPainel.CRIADAS).map { it.fase.id })
    }
}
