package com.grupo.controlze.data.local

import com.grupo.controlze.logic.FASES_ORIGINAIS
import org.junit.Assert.assertEquals
import org.junit.Test

class FaseMappersTest {
    @Test
    fun todasAsFasesOriginaisMantemSeusDadosNoBanco() {
        FASES_ORIGINAIS.forEach { fase ->
            assertEquals(fase, fase.paraEntity().paraFase())
        }
    }
}
