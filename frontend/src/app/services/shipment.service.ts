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
// private shipments: Shipment[] = [
    //     {
    //         id: 562356123,
    //         origin: "Frankfurt",
    //         destination: "Hamburg",
    //         customerId: "512351",
    //         createdDate: 1672873600,
    //         fragile: true,
    //         notifyCustomer: true,
    //         transportType: TransportType.AIR,
    //         temperatureRange: {
    //             id: 1,
    //             min: -20,
    //             max: -10
    //         }
    //     },
    //     {
    //         id: 562356123,
    //         origin: "Frankfurt",
    //         destination: "Hamburg",
    //         customerId: "512351",
    //         createdDate: 1672873600,
    //         fragile: true,
    //         notifyCustomer: true,
    //         transportType: TransportType.SEA,
    //         temperatureRange: {
    //             id: 1,
    //             min: -20,
    //             max: -10
    //         }
    //     },
    //     {
    //         id: 562356123,
    //         origin: "Frankfurt",
    //         destination: "Hamburg",
    //         customerId: "512351",
    //         createdDate: 1672873600,
    //         fragile: true,
    //         notifyCustomer: true,
    //         transportType: TransportType.ROAD,
    //         temperatureRange: {
    //             id: 1,
    //             min: -20,
    //             max: -10
    //         }
    //     }
    // ];

    getShipments(){
        return this.http.get<Shipment[]>("http://localhost:8080/shipments")
    }

    getShipmentById(shipmentId: Number): Shipment | null {
        return this.shipments.find(it => it.shipmentId == shipmentId) || null;
    }

}