import {Component, Input, OnInit} from '@angular/core';
import {Shipment, TransportType} from "../../models/shipment.mode";
import {ShipmentService} from "../../services/shipment.service";
import {TemplateService} from "../../services/template.service";
import {Template} from "../../models/template.mode";

@Component({
  selector: 'execution-plan-model',
  templateUrl: './execution-plan-model.component.html',
  styleUrls: ['./execution-plan-model.component.scss']
})
export class ExecutionPlanModelComponent implements OnInit {

  templates: Template[];

  @Input()
  shipment: Shipment;

  constructor(
      private templateService: TemplateService
  ) {
  }

  ngOnInit(): void {
    this.fetchTemplates()
  }
  fetchTemplates() {
    this.templateService.getTemplates().subscribe((data) => {
      this.templates = data;
    })
  }

  createExecutionPlan() {
    // alert(JSON.stringify(shipment))
  }
}
