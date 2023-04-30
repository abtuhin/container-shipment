package com.kn.containershipment.model

import jakarta.persistence.*

/**
 * ExecutionPlan is used to store information about shipment and its corresponding actions to be executed.
 */

@Entity
@Table
data class ExecutionPlan(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
        @JoinColumn(name = "fk_shipment_id")
        val shipment: Shipment? = null,

        val templateId: Long = 0,

        @OneToMany(targetEntity = ExecutionPlanAction::class, cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinColumn(name = "e_pta_fk", referencedColumnName = "id")
        val actions: List<ExecutionPlanAction> = listOf()
)

/**
 * ExecutionPlanAction is used to execution individual actions from the template actions in an ExecutionPlan
 */
@Entity
@Table
data class ExecutionPlanAction(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        val actionName: String? = null,

        val isExecuted: Boolean = false,

        val isNotify: Boolean = false
)
