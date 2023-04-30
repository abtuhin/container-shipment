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

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
        @JoinColumn(name = "fk_shipment_id")
        val shipment: Shipment? = null,

        val templateId: Long = 0,

        @OneToMany(mappedBy = "executionPlan", cascade = [CascadeType.ALL], orphanRemoval = true)
        val actions: MutableList<ExecutionPlanAction> = mutableListOf(),
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

        val isNotify: Boolean = false,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "execution_plan_id")
        val executionPlan: ExecutionPlan? = null

)
