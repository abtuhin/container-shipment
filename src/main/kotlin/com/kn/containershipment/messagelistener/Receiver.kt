package com.kn.containershipment.messagelistener

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.model.ExecutionPlanAction
import com.kn.containershipment.model.Shipment
import com.kn.containershipment.repository.*
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component


@Component
class Receiver(private val shipmentRepository: ShipmentRepository,
               private val temperatureRangeRepository: TemperatureRangeRepository,
               private val templateRepository: TemplateRepository,
               private val executionPlanRepository: ExecutionPlanRepository,
               private val executionPlanActionRepository: ExecutionPlanActionRepository) {

    val objectMapper = jacksonObjectMapper()

    @RabbitListener(queues = ["fanout.queue1"])
    fun receiveMessageFromFanout1(message: String) {
        val streamShipment: Shipment = objectMapper.readValue(message)

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

        val template = shipment.temperatureRange?.let { templateRepository.findByTemperatureRangeId(it.id) }
        val actions = template?.actions
        if (actions != null) {
            val executionPlan = ExecutionPlan(
                    shipment = shipment,
                    templateId = template.id,
            )

            for (action in actions) {
                val executionPlanAction = ExecutionPlanAction(actionName = action.name,
                        isNotify = shipment.notifyCustomer,
                        isExecuted = true,
                        executionPlan = executionPlan)
                executionPlan.actions.add(executionPlanAction)
            }

            executionPlanRepository.save(executionPlan)

        }


    }


}