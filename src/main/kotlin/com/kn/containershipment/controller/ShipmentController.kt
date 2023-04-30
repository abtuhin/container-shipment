package com.kn.containershipment.controller

import com.kn.containershipment.repository.ActionRepository
import com.kn.containershipment.repository.ShipmentRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ShipmentController(val shipmentRepository: ShipmentRepository) {


    @GetMapping("/shipments")
    fun getShipments() = shipmentRepository.findAll()

    @GetMapping("/shipments/{id}")
    fun getShipmentById(@PathVariable id: Long) = shipmentRepository.findById(id)

}