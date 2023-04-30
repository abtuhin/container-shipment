
import {Injectable} from "@angular/core";
import { HttpClient } from '@angular/common/http';
import {Plan} from "../models/plan.mode";

@Injectable({
    providedIn: 'root',
})
export class PlanService {
    plans: Plan[]
    constructor(private http: HttpClient) {}
    getPlans(){
        return this.http.get<Plan[]>("http://localhost:8080/execution-plans")
    }
}