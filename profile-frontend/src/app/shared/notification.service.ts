import {Injectable} from '@angular/core';
import {Observable, Subject} from "rxjs";
import {AppError, AppErrorType} from "./model";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  error$: Observable<any | Error>;
  info$: Observable<any | string>;
  private errorSubject = new Subject();
  private infoSubject = new Subject();

  constructor() {
    this.error$ = this.errorSubject.asObservable();
    this.info$ = this.infoSubject.asObservable();
  }

  handleError(error: any) {
    this.displayError(error);
  }

  displayInfo(message: string) {
    this.infoSubject.next(message);
  }

  displayError(error: any) {
    if (error && error.status === 0) {
      this.errorSubject.next(new AppError(
        "No Connectivity, Please check your internet connection", AppErrorType.OFFLINE));
    } else {
      const msg = [];
      error && error.status && msg.push(`(${error.status})`);
      error && error.statusText && msg.push(error.statusText);
      error && error.message && msg.push(error.message);
      this.errorSubject.next(new AppError(msg.join(" "), AppErrorType.TECHNICAL));
    }
  }
}
