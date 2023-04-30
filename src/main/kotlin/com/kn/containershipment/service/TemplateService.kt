package com.kn.containershipment.service

import com.kn.containershipment.model.PlanTemplate
import com.kn.containershipment.repository.TemplateRepository
import org.springframework.stereotype.Service
import java.util.Optional


@Service
class TemplateService(private val templateRepository: TemplateRepository) {

    fun getByTemperatureRangeId(id: Long): PlanTemplate {
        return templateRepository.findByTemperatureRangeId(id)
    }

    fun getById(id: Long): Optional<PlanTemplate> {
        return templateRepository.findById(id)
    }
}