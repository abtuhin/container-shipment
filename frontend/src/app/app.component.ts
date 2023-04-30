import {Component, OnInit} from '@angular/core';
import {Shipment, TransportType} from "./models/shipment.mode";
import {ShipmentService} from "./services/shipment.service";
import {PlanService} from "./services/plan.service";
import {Plan} from "./models/plan.mode";

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss']
})
export class AppComponent implements OnInit{

  AIR: TransportType = TransportType.AIR;
  ROAD: TransportType = TransportType.ROAD;
  SEA: TransportType = TransportType.SEA;

  shipments: Shipment[] = [];
  selectedShipments: Shipment[] = [];
  plans: Plan[] = [];

  constructor(
      private shipmentService: ShipmentService,
      private planService: PlanService
  ) {
  }

  ngOnInit(): void {
    this.fetchShipments();
    this.fetchPlans();
  }
  fetchShipments() {
    this.shipmentService.getShipments().subscribe(data => {
      this.shipments = data;
    });
  }

  fetchPlans() {
    this.planService.getPlans().subscribe(data => {
      this.plans = data;
    });
  }

  onHandleShipment(shipment: Shipment) {
    if(this.selectedShipments.find(i => i.shipmentId === shipment.shipmentId)){
      this.selectedShipments = this.selectedShipments.filter(i => i.shipmentId !== shipment.shipmentId)
    } else {
      this.selectedShipments.push(shipment)
    }
  }
}
