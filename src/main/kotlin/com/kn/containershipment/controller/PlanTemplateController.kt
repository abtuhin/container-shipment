package com.kn.containershipment.controller

import com.kn.containershipment.model.PlanTemplate
import com.kn.containershipment.repository.TemplateRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PlanTemplateController(val templateRepository: TemplateRepository){
    @GetMapping("/plan-templates")
    fun getTemplates(): MutableIterable<PlanTemplate> = templateRepository.findAll()
}