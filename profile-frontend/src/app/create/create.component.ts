import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {MasterdataService} from "../shared/masterdata.service";
import {Observable, Subject} from "rxjs";
import {CityTo, ProfileTo, SingleValueTo} from "../shared/model";
import {ProfileService} from "../shared/profile.service";
import {debounceTime, distinctUntilChanged, filter, takeUntil} from "rxjs/operators";
import {Router} from "@angular/router";
import {NotificationService} from "../shared/notification.service";

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent implements OnInit, OnDestroy {

  destroy$ = new Subject<boolean>();
  registerForm = this.fb.group({
    displayName: [null, Validators.compose(
      [Validators.required, Validators.maxLength(256)]
    )],
    realName: [null, Validators.compose(
      [Validators.required, Validators.maxLength(256)]
    )],
    birthDate: [null, Validators.required],
    ethnicity: null,
    gender: [null, Validators.required],
    height: null,
    religion: null,
    maritalStatus: [null, Validators.required],
    occupation: [null, Validators.maxLength(256)],
    figure: null,
    city: [null, Validators.required],
    aboutMe: [null, Validators.maxLength(5000)]
  });
  allCities: CityTo[];
  citySuggestions: string[];
  selectedCity: CityTo;

  religion$: Observable<SingleValueTo[]>;
  ethnicity$: Observable<SingleValueTo[]>;
  figure$: Observable<SingleValueTo[]>;
  gender$: Observable<SingleValueTo[]>;
  maritalStatus$: Observable<SingleValueTo[]>;

  constructor(private fb: FormBuilder,
              private masterdataService: MasterdataService,
              private profileService: ProfileService,
              private router: Router,
              private notificationService: NotificationService) {
  }

  ngOnInit() {
    this.religion$ = this.masterdataService.getReligion();
    this.ethnicity$ = this.masterdataService.getEthnicity();
    this.figure$ = this.masterdataService.getFigure();
    this.gender$ = this.masterdataService.getGender();
    this.maritalStatus$ = this.masterdataService.getMaritalStatus();
    this.masterdataService.getCity()
      .pipe(takeUntil(this.destroy$))
      .subscribe(cities => this.allCities = cities);

    this.registerForm.controls['city'].valueChanges
      .pipe(
        takeUntil(this.destroy$),
        filter(c => c && c.length),
        debounceTime(1000),
        distinctUntilChanged(),
      )
      .subscribe(newValue => {
        this.citySuggestions = this.allCities
          .filter(c => c.name.toLowerCase().includes(newValue.toLowerCase()))
          .map(c => c.name)
      });
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const profile = this.createProfile();
      this.profileService.saveProfile(profile).pipe(takeUntil(this.destroy$))
        .subscribe(savedProfile => {
          this.notificationService.displayInfo("Profile saved successfully!");
          this.router.navigate([`view/${savedProfile.id}`]);
        })
    }
  }

  ngOnDestroy() {
    this.destroy$.next(true);
  }

  private createProfile(): ProfileTo {
    const profile = new ProfileTo();
    profile.displayName = this.registerForm.controls['displayName'].value;
    profile.realName = this.registerForm.controls['realName'].value;
    profile.gender = this.registerForm.controls['gender'].value;
    profile.city = this.selectedCity;
    profile.about = this.registerForm.controls['aboutMe'].value;
    profile.birthDate = this.registerForm.controls['birthDate'].value;
    profile.ethnicity = this.registerForm.controls['ethnicity'].value;
    profile.height = this.registerForm.controls['height'].value;
    profile.figure = this.registerForm.controls['figure'].value;
    profile.religion = this.registerForm.controls['religion'].value;
    profile.maritalStatus = this.registerForm.controls['maritalStatus'].value;
    profile.occupation = this.registerForm.controls['occupation'].value;
    return profile;
  }

  validateCity(e: Event) {
    let value = e.target['value'];
    value = (value && value.trim()) || '';
    const city = this.allCities.find(c => c.name.toLowerCase() === value.toLowerCase());
    if (city) {
      this.registerForm.controls['city'].setValue(value);
      this.selectedCity = city;
    } else {
      this.registerForm.controls['city'].setValue(null);
    }
  }
}
