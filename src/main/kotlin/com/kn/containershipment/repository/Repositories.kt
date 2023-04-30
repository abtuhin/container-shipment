package com.kn.containershipment.repository

import com.kn.containershipment.model.*
import org.springframework.data.repository.CrudRepository

interface TemplateRepository : CrudRepository<PlanTemplate, Long> {
    fun findByTemperatureRangeId(id: Long): PlanTemplate
}

interface ActionRepository : CrudRepository<Action, Long>
interface TemperatureRangeRepository : CrudRepository<TemperatureRange, Long> {
    fun findByMinGreaterThanEqualAndAndMaxLessThanEqual(min: Int, max: Int): TemperatureRange
}

interface ShipmentRepository : CrudRepository<Shipment, Long>

interface ExecutionPlanRepository : CrudRepository<ExecutionPlan, Long>

interface ExecutionPlanActionRepository : CrudRepository<ExecutionPlanAction, Long>