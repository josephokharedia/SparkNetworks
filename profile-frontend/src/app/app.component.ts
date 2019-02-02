import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {AppError} from "./shared/model";
import {NotificationService} from "./shared/notification.service";
import {Subject} from "rxjs";
import {takeUntil} from "rxjs/operators";
import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {

  appError: AppError;
  destroy$ = new Subject<boolean>();

  constructor(private notificationService: NotificationService,
              private snackBar: MatSnackBar,
              private cdr: ChangeDetectorRef) {
  }

  ngOnInit() {
    this.notificationService.error$.pipe(takeUntil(this.destroy$))
      .subscribe(error => {
        this.appError = error;
        this.cdr.detectChanges();
      });
    this.notificationService.info$.pipe(takeUntil(this.destroy$))
      .subscribe(info => {
        this.showInfo(info, 'DISMISS');
        this.cdr.detectChanges();
      });
  }

  showInfo(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000, verticalPosition: "top", horizontalPosition: "right"
    });
  }

  ngOnDestroy() {
    this.destroy$.next(true);
  }

  dismiss() {
    this.appError = null;
    this.cdr.detectChanges();
  }
}
