package com.kn.containershipment

import com.kn.containershipment.model.PlanTemplate
import com.kn.containershipment.repository.TemplateRepository
import com.kn.containershipment.service.TemplateService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Optional

class TemplateServiceTest {

    private val templateRepository: TemplateRepository = mockk()
    private val templateService = TemplateService(templateRepository)

    @Test
    fun whenGetTemplate_thenReturnTemplate() {

        val planTemplate = Optional.of(PlanTemplate())
        //given
        every { templateRepository.findById(1) } returns planTemplate;

        //when
        val result = templateService.getById(1);

        //then
        verify(exactly = 1) { templateRepository.findById(1) };
        assertEquals(planTemplate, result)
    }

    @Test
    fun testGetByTemperatureRangeId() {
        val planTemplate = PlanTemplate()

        //given
        every { templateRepository.findByTemperatureRangeId(2) } returns planTemplate

        //when
        val result = templateService.getByTemperatureRangeId(2)

        //then
        verify(exactly = 1) { templateRepository.findByTemperatureRangeId(2) };
        assertEquals(planTemplate, result)

    }


}