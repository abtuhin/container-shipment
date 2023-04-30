package com.kn.containershipment.controller

import com.kn.containershipment.dto.ExecutionPlanDto
import com.kn.containershipment.dto.TemperatureRangeDto
import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.repository.ExecutionPlanRepository
import org.modelmapper.ModelMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class ExecutionPlanController(val executionPlanRepository: ExecutionPlanRepository,
        val modelMapper: ModelMapper) {
    @GetMapping("/execution-plans")
    fun getExecutionPlans(): List<ExecutionPlanDto?> {
        var plans = executionPlanRepository.findAll()

        val executionPlans: MutableIterable<ExecutionPlan> = executionPlanRepository.findAll()
        return executionPlans.map(this::convertToDto)
    }

    private fun convertToDto(executionPlan: ExecutionPlan): ExecutionPlanDto {
        val executionPlanDto: ExecutionPlanDto = modelMapper.map(executionPlan, ExecutionPlanDto::class.java)

        val temp = executionPlan.shipment?.temperatureRange?.let { TemperatureRangeDto(it.id,
                it.min,
                it.max) }


        executionPlanDto.shipment?.temperatureRange = temp
        return executionPlanDto
    }
}