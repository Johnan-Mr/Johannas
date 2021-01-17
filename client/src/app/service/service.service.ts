import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Service } from './service';

@Injectable({
    providedIn: 'root',
})
export class ServiceService {
    private statusUrl = 'http://localhost:8080/status';
    private serviceUrl = 'http://localhost:8080/services';

    constructor(private http: HttpClient) {

    }

    public getServices(): Observable<Service[]> {
        return this.http.get<Service[]>(this.serviceUrl, { responseType: 'json' }).
            pipe(catchError(this.handleError<Service[]>('getServices', []))
            );
    }

    public saveService(service: Service): Observable<Service> {
        return this.http.post<Service>(this.serviceUrl, service).pipe(
            tap((newService: Service) =>
                catchError(this.handleError<Service>('addService'))
            ));
    }

    public pollServices(): Observable<Object> {
        return this.http.post(this.statusUrl, {observe: 'response'});
    }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(error);
            return of(result as T);
        }
    }

}