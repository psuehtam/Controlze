package com.grupo.controlze.data.remote

import com.grupo.controlze.logic.FASES_ORIGINAIS
import org.junit.Assert.assertEquals
import org.junit.Test

class FaseDtoTest {
    @Test
    fun todasAsFasesOriginaisMantemSeusDadosNoFirestore() {
        FASES_ORIGINAIS.forEach { fase ->
            assertEquals(fase, fase.paraDto().paraFase(fase.id))
        }
    }
}
