package com.kn.containershipment.service

import com.kn.containershipment.dto.ExecutionPlanDto
import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.model.ExecutionPlanAction
import com.kn.containershipment.model.Shipment
import com.kn.containershipment.repository.*
import org.springframework.stereotype.Service

@Service
class ExecutionPlanService(
        private val temperatureRangeRepository: TemperatureRangeRepository,
        private val templateService: TemplateService,
        private val executionPlanRepository: ExecutionPlanRepository,
        private val shipmentRepository: ShipmentRepository
) {
    fun createExecutionPlan(streamShipment: Shipment) {
        val shipment = Shipment(
                id = streamShipment.id,
                origin = streamShipment.origin,
                destination = streamShipment.destination,
                customerId = streamShipment.customerId,
                createdDate = streamShipment.createdDate,
                fragile = streamShipment.fragile,
                notifyCustomer = streamShipment.notifyCustomer,
                transportType = streamShipment.transportType
        )
        val temperatureRange = streamShipment.temperatureRange?.let {
            temperatureRangeRepository.findByMinGreaterThanEqualAndAndMaxLessThanEqual(it.min,
                    it.max)
        }

        shipment.temperatureRange = temperatureRange

        val template = shipment.temperatureRange?.let { templateService.getByTemperatureRangeId(it.id) }
        val actions = template?.actions
        if (actions != null) {
            val executionPlan = ExecutionPlan(
                    shipment = shipment,
                    templateId = template.id,
            )

            for (action in actions) {
                val executionPlanAction = ExecutionPlanAction(
                        actionName = action.name,
                        isNotify = shipment.notifyCustomer,
                        isExecuted = true,
                        executionPlan = executionPlan
                )
                executionPlan.actions.add(executionPlanAction)
            }
            executionPlanRepository.save(executionPlan)
        }
    }

    fun createExecutionPlan(executionPlanDto: ExecutionPlanDto): ExecutionPlan {

        val shipment = executionPlanDto.shipment?.id?.let { shipmentRepository.findById(it).get() }

        val template = templateService.getById(executionPlanDto.templateId).get()

        val actions = template.actions

        val executionPlan = ExecutionPlan(
                shipment = shipment,
                templateId = template.id,
        )

        for (action in actions) {
            val executionPlanAction = shipment?.let {
                ExecutionPlanAction(
                        actionName = action.name,
                        isNotify = it.notifyCustomer,
                        isExecuted = true,
                        executionPlan = executionPlan
                )
            }
            if (executionPlanAction != null) {
                executionPlan.actions.add(executionPlanAction)
            }
        }
        return executionPlanRepository.save(executionPlan)
    }
}