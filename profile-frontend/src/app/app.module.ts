import {BrowserModule} from '@angular/platform-browser';
import {ErrorHandler, NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {
  MatAutocompleteModule,
  MatButtonModule,
  MatCardModule,
  MatDatepickerModule,
  MatDividerModule, MatIconModule,
  MatInputModule,
  MatNativeDateModule,
  MatRadioModule,
  MatSelectModule, MatSnackBarModule
} from '@angular/material';
import {ReactiveFormsModule} from '@angular/forms';
import {CreateComponent} from "./create/create.component";
import {HttpClientModule} from "@angular/common/http";
import {NgProgressModule} from '@ngx-progressbar/core';
import { ViewComponent } from './view/view.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ErrorHandlerService} from "./shared/error-handler.service";

@NgModule({
  declarations: [
    AppComponent,
    CreateComponent,
    ViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatIconModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    MatDividerModule,
    HttpClientModule,
    NgProgressModule,
    MatAutocompleteModule,
  ],
  providers: [
    {provide: ErrorHandler, useClass: ErrorHandlerService},
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
