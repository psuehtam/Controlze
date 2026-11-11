package com.grupo.controlze.data.local

import com.grupo.controlze.logic.FASES_ORIGINAIS
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class FaseMappersTest {
    @Test
    fun todasAsFasesOriginaisMantemSeusDadosNoBanco() {
        FASES_ORIGINAIS.forEach { fase ->
            assertEquals(fase, fase.paraEntity().paraFase())
        }
    }

    @Test
    fun tipoAntigoNoBancoEIgnorado() {
        assertNull(FASES_ORIGINAIS[0].paraEntity().copy(tipo = "BOTAO_FOGE").paraFase())
    }
}
