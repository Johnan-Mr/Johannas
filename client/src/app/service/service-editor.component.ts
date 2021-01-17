import { Component, EventEmitter, Output } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Service } from "./service";
import { ServiceService } from "./service.service";

@Component({
    selector: 'app-service-editor',
    templateUrl: './service-editor.component.html',
    styleUrls: ['./service-editor.component.scss']
})

export class ServiceEditorComponent {
    @Output() serviceEvent: EventEmitter<Service> = new EventEmitter<Service>();

    serviceForm: FormGroup;

    constructor(private serviceService: ServiceService, private formBuilder: FormBuilder) {
        this.serviceForm = this.formBuilder.group({
            url: ['', Validators.required],
            name: ['', Validators.required],
        })
    }

    onSubmit(): void {     
        this.serviceService.saveService(new Service(this.serviceForm.value)).subscribe();
        this.serviceEvent.emit(new Service(this.serviceForm.value));
        this.serviceForm.reset();
    }
}