import { Component, OnDestroy, OnInit } from '@angular/core';
import { interval, Subscription } from 'rxjs';
import { startWith, switchMap } from 'rxjs/operators';
import { Service } from './service/service';
import { ServiceService } from './service/service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
  timeInterval?: Subscription;
  title = 'Kry Service';
  serviceList: Service[] = [];
  
  constructor(private serviceService: ServiceService){
  }
  
  ngOnDestroy(): void {
    this.timeInterval?.unsubscribe;
  }

  ngOnInit(): void {
    this.timeInterval = interval(5000)
    .pipe(
      startWith(0),
      switchMap(() => this.serviceService.pollServices())
    ).subscribe();
  }
}
