import {
  Component,
  ChangeDetectorRef,
  NgZone,
  OnInit
} from '@angular/core';

import { CommonModule } from '@angular/common';

import { Order } from '../../models/order';

import { OrderService }
from '../../services/order.service';

import { RealtimeService }
from '../../services/realtime.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class DashboardComponent
implements OnInit {

  orders: Order[] = [];

  constructor(
    private ngZone: NgZone,
    private cdr: ChangeDetectorRef,
    private orderService: OrderService,
    private realtimeService: RealtimeService
  ) {}

  ngOnInit(): void {

    this.loadOrders();

    this.connectRealtime();
  }

  loadOrders(): void {

    this.orderService.getOrders()
      .subscribe(data => {

        this.orders = data
          .map(order => ({

            ...order,

            orderId:
              Number(
                (order as any).orderId
                ||
                (order as any).id
              )
          }))
          .reverse();

        console.log(
          'INITIAL ORDERS',
          this.orders
        );

        this.cdr.detectChanges();
      });
  }

  connectRealtime(): void {

    this.realtimeService.connect()
      .subscribe({

        next: (order) => {

          this.handleIncomingOrder(order);
        },

        error: (error) => {

          console.error(
            'REALTIME ERROR',
            error
          );
        }
      });
  }

  handleIncomingOrder(order: any): void {

    console.log(
      'INCOMING ORDER',
      order
    );

    this.ngZone.run(() => {

      const incomingOrderId =
        Number(order.orderId || order.id);

      const existingOrderIndex =
        this.orders.findIndex(
          o => Number(o.orderId) === incomingOrderId
        );

      // UPDATE EXISTING ORDER

      if (existingOrderIndex !== -1) {

        this.orders[existingOrderIndex] = {

          ...this.orders[existingOrderIndex],
          ...order,
          orderId: incomingOrderId
        };

        this.orders = [...this.orders];

        console.log(
          'UPDATED EXISTING ORDER'
        );
      }

      // INSERT NEW ORDER

      else {

        this.orders = [
          {
            ...order,
            orderId: incomingOrderId
          },
          ...this.orders
        ];

        console.log(
          'NEW ORDER INSERTED'
        );
      }

      this.cdr.detectChanges();

      console.log(
        'UPDATED ORDERS',
        this.orders
      );
    });
  }
}