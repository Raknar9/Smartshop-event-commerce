import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RealtimeService {

  connect(): Observable<any> {

    return new Observable(observer => {

      console.log('CONNECTING TO SSE...');

      const eventSource = new EventSource(
        'http://localhost:8080/realtime/events'
      );

      eventSource.onopen = () => {

        console.log('SSE CONNECTED');
      };

      // ORDER CREATED

      eventSource.addEventListener(
        'order-created',
        (event: any) => {

          console.log('ORDER CREATED', event);

          observer.next(
            JSON.parse(event.data)
          );
        }
      );

      // STOCK RESERVED

      eventSource.addEventListener(
        'stock-reserved',
        (event: any) => {

          console.log('STOCK RESERVED', event);

          observer.next(
            JSON.parse(event.data)
          );
        }
      );

      // PAYMENT COMPLETED

      eventSource.addEventListener(
        'payment-completed',
        (event: any) => {

          console.log('PAYMENT COMPLETED', event);

          observer.next(
            JSON.parse(event.data)
          );
        }
      );

      eventSource.onerror = error => {

        console.error('SSE ERROR', error);
      };

      return () => {

        eventSource.close();
      };
    });
  }
}