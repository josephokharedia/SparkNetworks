import {Injectable} from '@angular/core';
import {CityTo, SingleValueTo} from "./model";
import {Observable, of} from "rxjs";
import {HttpClient} from "@angular/common/http";

const HTTP_ETHNICITY_URL = "api/masterdata/ethnicity";
const HTTP_GENDER_URL = "/api/masterdata/gender";
const HTTP_RELIGION_URL = "/api/masterdata/religion";
const HTTP_FIGURE_URL = "/api/masterdata/figure";
const HTTP_MARITAL_STATUS = "/api/masterdata/maritalStatus";
const HTTP_LOCATION_STATUS = "/api/masterdata/city";

@Injectable({
  providedIn: 'root'
})
export class MasterdataService {

  constructor(private http: HttpClient) {
  }

  getGender(): Observable<SingleValueTo[]> {
    return this.http.get<SingleValueTo[]>(HTTP_GENDER_URL);
  }

  getEthnicity(): Observable<SingleValueTo[]> {
    return this.http.get<SingleValueTo[]>(HTTP_ETHNICITY_URL);
  }

  getReligion(): Observable<SingleValueTo[]> {
    return this.http.get<SingleValueTo[]>(HTTP_RELIGION_URL);
  }

  getFigure(): Observable<SingleValueTo[]> {
    return this.http.get<SingleValueTo[]>(HTTP_FIGURE_URL);
  }

  getMaritalStatus(): Observable<SingleValueTo[]> {
    return this.http.get<SingleValueTo[]>(HTTP_MARITAL_STATUS);
  }

  getCity(): Observable<CityTo[]> {
    return this.http.get<CityTo[]>(`${HTTP_LOCATION_STATUS}`);
  }
}

const dummyGenderList = of([
  new SingleValueTo(1, "Male"),
  new SingleValueTo(2, "Female"),
  new SingleValueTo(3, "Other"),
]);

const dummyEthnicityList = of([
  new SingleValueTo(1, "Black"),
  new SingleValueTo(2, "White"),
  new SingleValueTo(3, "Indian"),
]);

const dummyReligionList = of([
  new SingleValueTo(1, "Christian"),
  new SingleValueTo(2, "Muslim"),
  new SingleValueTo(3, "Atheist"),
]);

const dummyFigureList = of([
  new SingleValueTo(1, "Slim"),
  new SingleValueTo(2, "Fat"),
  new SingleValueTo(3, "Medium"),
]);

const dummyMaritalStatusList = of([
  new SingleValueTo(1, "Single"),
  new SingleValueTo(2, "Married"),
  new SingleValueTo(3, "Complicated"),
]);

const dummyLocationList = of([
  new CityTo(1, "Johannesburg"),
  new CityTo(2, "Capetown"),
  new CityTo(3, "Durban"),
]);
