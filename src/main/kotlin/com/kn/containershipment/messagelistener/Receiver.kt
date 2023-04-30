package com.kn.containershipment.messagelistener

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.kn.containershipment.model.Shipment
import com.kn.containershipment.service.ExecutionPlanService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component


@Component
class Receiver(val executionPlanService: ExecutionPlanService) {
    val objectMapper = jacksonObjectMapper()

    @RabbitListener(queues = ["fanout.queue1"])
    fun receiveMessageFromFanout1(message: String) {
        val streamShipment: Shipment = objectMapper.readValue(message)
        executionPlanService.createExecutionPlan(streamShipment)
    }
}