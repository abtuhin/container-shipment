package com.kn.containershipment.controller

import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.repository.ExecutionPlanRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExecutionPlanController(val executionPlanRepository: ExecutionPlanRepository) {
    @GetMapping("/execution-plans")
    fun getExecutionPlans(): MutableIterable<ExecutionPlan> = executionPlanRepository.findAll()
}