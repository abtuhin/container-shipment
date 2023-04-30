import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { ExecutionPlanModelComponent } from './components/execution-plan-model/execution-plan-model.component';
import {ShipmentService} from "./services/shipment.service";
import { ExecutionPlanComponentComponent } from './components/execution-plan/execution-plan-component.component';
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    ExecutionPlanModelComponent,
    ExecutionPlanComponentComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ShipmentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
