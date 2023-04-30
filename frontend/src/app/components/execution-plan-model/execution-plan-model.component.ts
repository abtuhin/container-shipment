import {Component, Input, OnInit} from '@angular/core';
import {Shipment, TransportType} from "../../models/shipment.mode";
import {ShipmentService} from "../../services/shipment.service";
import {TemplateService} from "../../services/template.service";
import {Template} from "../../models/template.mode";
import {PlanService} from "../../services/plan.service";

@Component({
  selector: 'execution-plan-model',
  templateUrl: './execution-plan-model.component.html',
  styleUrls: ['./execution-plan-model.component.scss']
})
export class ExecutionPlanModelComponent implements OnInit {

  templates: Template[];
  selectedTemplate: Template

  @Input()
  selectedShipments: Shipment[];

  constructor(
      private templateService: TemplateService,
      private planService: PlanService
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
    this.selectedShipments.map((shipment) => {
      const execPlan = {
        shipment,
        templateId: this.selectedTemplate.id
      }

      this.planService.createPlan(execPlan).subscribe(
          res => {
            console.log('Execution plan created successfully');
          },
          error => {
            console.error('Error creating execution plan', error);
          }
      );
    })
  }

  onHandleTemplate(template: Template) {
    this.selectedTemplate = template;
  }
}
