import { OnInit } from "@angular/core";
import { Component } from "@angular/core";
import { Subscription } from "rxjs/internal/Subscription";
import { Service } from "./service";
import { ServiceService } from "./service.service";

@Component({
  selector: 'app-service-list',
  templateUrl: './service-list.component.html',
  styleUrls: ['./service-list.component.scss']
})

export class ServiceListComponent implements OnInit {
  services: Service[] = [];
  private subscription?: Subscription;

  constructor(private serviceService: ServiceService) { }

  ngOnInit(): void {
    this.subscription = this.serviceService.getServices().subscribe(services => {
      this.services = services;
    })
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe;
  }

  updateList(service: Service): void {
    this.services.push(service);
    window.location.href = window.location.href;
  }
}