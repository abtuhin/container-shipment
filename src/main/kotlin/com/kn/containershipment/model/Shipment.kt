package com.kn.containershipment.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table
data class Shipment(


        @Id
        @JsonProperty("shipmentId")
        val id: Long = 0,

        val origin: String? = null,

        val destination: String? = null,

        val customerId: String? = null,

        val createdDate: Long = 0,

        val fragile: Boolean = false,

        val notifyCustomer: Boolean = false,


        @Enumerated(EnumType.STRING)
        val transportType: TransportType? = null,

        @OneToOne(cascade = [CascadeType.MERGE], orphanRemoval = true, fetch = FetchType.EAGER)
        @JoinColumn(name = "fk_temperature_range_id")
        @JsonProperty("temperature")
        var temperatureRange: TemperatureRange? = null
)

enum class TransportType {
    AIR,
    SEA,
    ROAD
}

@Entity
@Table
data class TemperatureRange(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,
        val min: Int = 0,
        val max: Int = 0
)
