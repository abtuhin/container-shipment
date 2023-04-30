package com.kn.containershipment.messagelistener

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kn.containershipment.model.ExecutionPlan
import com.kn.containershipment.model.ExecutionPlanAction
import com.kn.containershipment.model.Shipment
import com.kn.containershipment.repository.*
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import java.util.concurrent.ExecutionException


@Component
class Receiver(private val shipmentRepository: ShipmentRepository,
               private val temperatureRangeRepository: TemperatureRangeRepository,
               private val templateRepository: TemplateRepository,
               private val executionPlanRepository: ExecutionPlanRepository,
               private val executionPlanActionRepository: ExecutionPlanActionRepository) {

    val objectMapper = jacksonObjectMapper()

    @RabbitListener(queues = ["fanout.queue1"])
    fun receiveMessageFromFanout1(message: String) {
        println("Received fanout 1 message: $message")


        var shipment: Shipment = objectMapper.readValue(message)

        var temperatureRange = shipment.temperatureRange?.let {
            temperatureRangeRepository.findByMinGreaterThanEqualAndAndMaxLessThanEqual(it.min,
                    it.max)
        }

        shipment.temperatureRange = temperatureRange
        shipmentRepository.save(shipment)

        val template = temperatureRange?.let { templateRepository.findByTemperatureRangeId(it.id) }

        val actions = template?.actions
        if (actions != null) {

            val executionPlanActions: MutableList<ExecutionPlanAction> = mutableListOf()
            for (action in actions) {
                executionPlanActions.add(
                        ExecutionPlanAction(action.name, shipment.notifyCustomer, true)
                )
            }
            executionPlanActionRepository.saveAll(executionPlanActions)


            val executionPlan = ExecutionPlan(
                    0,
                    shipment,
                    template.id,
                    executionPlanActions
            )
            executionPlanRepository.save(executionPlan)

        }


    }


}