
import {Injectable} from "@angular/core";
import { HttpClient } from '@angular/common/http';
import {Template} from "../models/template.mode";

@Injectable({
    providedIn: 'root',
})
export class TemplateService {
    templates: Template[]
    constructor(private http: HttpClient) {}
    getTemplates(){
        return this.http.get<Template[]>("http://localhost:8080/plan-templates")
    }
}