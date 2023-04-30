import {Component, Input, OnInit} from '@angular/core';
import {PlanService} from "../../services/plan.service";
import {Plan} from "../../models/plan.mode";

@Component({
  selector: 'execution-plan-component',
  templateUrl: './execution-plan-component.component.html',
  styleUrls: ['./execution-plan-component.component.scss']
})
export class ExecutionPlanComponentComponent implements OnInit {

  @Input()
  plans: Plan[]
  constructor() {
  }
  ngOnInit(): void {
  }
}
