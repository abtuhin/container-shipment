import { Component, OnInit } from '@angular/core';
import {PlanService} from "../../services/plan.service";
import {Plan} from "../../models/plan.mode";

@Component({
  selector: 'execution-plan-component',
  templateUrl: './execution-plan-component.component.html',
  styleUrls: ['./execution-plan-component.component.scss']
})
export class ExecutionPlanComponentComponent implements OnInit {

  plans: Plan[]
  constructor(private planService: PlanService) {
  }
  ngOnInit(): void {
    this.fetchPlans()
  }
  fetchPlans() {
    this.planService.getPlans().subscribe((data) => {
      this.plans = data;
    })
  }

}
