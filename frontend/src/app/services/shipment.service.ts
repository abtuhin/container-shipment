import {Shipment, TransportType} from "../models/shipment.mode";
import {Injectable} from "@angular/core";
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root',
})
export class ShipmentService {
    shipments: Shipment[]
    constructor(private http: HttpClient) {}
    getShipments(){
        return this.http.get<Shipment[]>("http://localhost:8080/shipments")
    }

    getShipmentById(shipmentId: Number): Shipment | null {
        return this.shipments.find(it => it.shipmentId == shipmentId) || null;
    }

}