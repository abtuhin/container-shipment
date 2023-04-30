import {Shipment} from "./shipment.mode";
import {Action} from "./template.mode";
export interface PlanAction {
    id: Number,
    actionName: String,
    isExecuted: boolean,
    isNotify: boolean
}
export interface Plan {
    id: Number,
    shipment: Shipment,
    templateId: String,
    actions: PlanAction[]
}
