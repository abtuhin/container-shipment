package com.kn.containershipment.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.kn.containershipment.model.TransportType

/**
 * ExecutionPlan is used to store information about shipment and its corresponding actions to be executed.
 */


data class ExecutionPlanDto(
        var id: Long = 0,
        var shipment: ShipmentDto? = null,
        var templateId: Long = 0,
        var actions: List<ExecutionPlanActionDto>? = null
) {
    constructor() : this(0)
}

data class ExecutionPlanActionDto(
        var id: Long = 0,
        var actionName: String? = null,
        var isExecuted: Boolean = false,
        var isNotify: Boolean = false,
) {
    constructor() : this(0)
}

data class ShipmentDto(

        @JsonProperty("shipmentId")
        var id: Long = 0,
        var origin: String? = null,
        var destination: String? = null,
        var customerId: String? = null,
        var createdDate: Long = 0,
        var fragile: Boolean = false,
        var notifyCustomer: Boolean = false,
        var transportType: TransportType? = null,
        var temperatureRange: TemperatureRangeDto? = null
) {
    constructor() : this(0)
}

data class TemperatureRangeDto(

        val id: Long=0,
        val min: Int=0,
        val max: Int=0
){
    constructor(): this(0)
}


