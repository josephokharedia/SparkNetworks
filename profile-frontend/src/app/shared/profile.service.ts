import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CityTo, ProfileTo, SingleValueTo} from "./model";

const HTTP_PROFILE_URL = "/api/profile";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) {
  }

  getProfile(id: number): Observable<ProfileTo> {
    return this.http.get<ProfileTo>(`${HTTP_PROFILE_URL}/${id}`);
  }

  saveProfile(profile: ProfileTo): Observable<ProfileTo> {
    return this.http.post<ProfileTo>(HTTP_PROFILE_URL, profile);
  }

  uploadAvatar(profileId: number, file: File): Observable<string> {
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post(`${HTTP_PROFILE_URL}/${profileId}/image`, formData, {responseType: 'text'});
  }

  getAvatar(profileId: number): Observable<string> {
    return this.http.get(`${HTTP_PROFILE_URL}/${profileId}/image`, {responseType: 'text'});
  }
}

function getDummyProfile(): ProfileTo {
  const dummyProfile = new ProfileTo();
  dummyProfile.figure = new SingleValueTo(1, "Slim");
  dummyProfile.occupation = "Software engineer";
  dummyProfile.maritalStatus = new SingleValueTo(1, "Married");
  dummyProfile.religion = new SingleValueTo(1, "Christian");
  dummyProfile.height = 1.4;
  dummyProfile.ethnicity = new SingleValueTo(1, "Black");
  dummyProfile.birthDate = new Date();
  dummyProfile.about = "Just a regular software engineer";
  dummyProfile.realName = "Joseph Okharedia";
  dummyProfile.displayName = "Joe";
  dummyProfile.city = new CityTo(1, "Capetown");
  dummyProfile.gender = new SingleValueTo(1, "Male");
  return dummyProfile;
}
