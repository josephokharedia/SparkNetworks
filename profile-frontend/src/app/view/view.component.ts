import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProfileService} from "../shared/profile.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CityTo, ProfileTo, SingleValueTo} from "../shared/model";
import {Observable, Subject} from "rxjs";
import {MasterdataService} from "../shared/masterdata.service";
import {debounceTime, distinctUntilChanged, filter, takeUntil} from "rxjs/operators";
import {NotificationService} from "../shared/notification.service";

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit, OnDestroy {

  destroy$ = new Subject<boolean>();
  profileForm: FormGroup;
  editMode = false;
  profileId: number;
  profile: ProfileTo;
  errorRetrievingProfile = false;
  religion$: Observable<SingleValueTo[]>;
  ethnicity$: Observable<SingleValueTo[]>;
  figure$: Observable<SingleValueTo[]>;
  gender$: Observable<SingleValueTo[]>;
  maritalStatus$: Observable<SingleValueTo[]>;

  allCities: CityTo[];
  citySuggestions: string[];
  selectedCity: CityTo;
  profilePicture: any;

  constructor(private fb: FormBuilder,
              private profileService: ProfileService,
              private masterdataService: MasterdataService,
              private route: ActivatedRoute,
              private router: Router,
              private notificationService: NotificationService) {
  }


  ngOnInit() {
    this.profileId = this.route.snapshot.params['id'];
    this.religion$ = this.masterdataService.getReligion();
    this.ethnicity$ = this.masterdataService.getEthnicity();
    this.figure$ = this.masterdataService.getFigure();
    this.gender$ = this.masterdataService.getGender();
    this.maritalStatus$ = this.masterdataService.getMaritalStatus();
    this.masterdataService.getCity().pipe(takeUntil(this.destroy$))
      .subscribe(cities => this.allCities = cities);
    this.profileForm = this.fb.group({
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
      aboutMe: [null, Validators.maxLength(5000)],
      avatar: null,
    });
    this.getProfile();
  }

  onEdit() {
    this.editMode = true;
    this.resetWithProfileInfo();
  }

  onCancel() {
    this.editMode = false;
    this.resetWithProfileInfo();
  }

  onSubmit() {
    if (this.profileForm.valid) {
      this.updateProfile();
      this.notificationService.displayInfo('Saving profile ...');
      this.profileService.saveProfile(this.profile)
        .subscribe((profile) => {
          this.profile = profile;
          this.editMode = false;
          this.resetWithProfileInfo();
          this.notificationService.displayInfo('Profile saved successfully!');
        });
    }
  }

  validateCity(e: Event) {
    let value = e.target['value'];
    value = (value && value.trim()) || '';
    const city = this.allCities.find(c => c.name.toLowerCase() === value.toLowerCase());
    if (city) {
      this.profileForm.controls['city'].setValue(value);
      this.selectedCity = city;
    } else {
      this.profileForm.controls['city'].setValue(null);
    }
  }

  compareById(o1: any, o2: any) {
    if (o1 == null || o2 == null) {
      return false;
    }
    return o1.id === o2.id;
  }

  private getProfile(): void {
    if (!this.profileId) {
      this.router.navigate(['create']);
      return;
    }
    this.profileService.getProfile(this.profileId).subscribe(profile => {
      this.profile = profile;
      this.selectedCity = this.profile.city;
      this.resetWithProfileInfo();
    }, (error) => this.errorRetrievingProfile = !!error);
    this.profileService.getAvatar(this.profileId)
      .pipe(takeUntil(this.destroy$), filter(base64 => !!base64))
      .subscribe(base64 => this.profilePicture = base64);
  }

  private resetWithProfileInfo() {
    const action = this.editMode ? 'enable' : 'disable';
    this.profileForm.controls['displayName'].setValue(this.profile.displayName);
    this.profileForm.controls['displayName'][action]();
    this.profileForm.controls['displayName'].setValidators(Validators.compose(
      [Validators.required, Validators.maxLength(256)]
    ));
    this.profileForm.controls['realName'].setValue(this.profile.realName);
    this.profileForm.controls['realName'][action]();
    this.profileForm.controls['realName'].setValidators(Validators.compose(
      [Validators.required, Validators.maxLength(256)]
    ));
    this.profileForm.controls['birthDate'].setValue(new Date(this.profile.birthDate));
    this.profileForm.controls['birthDate'][action]();
    this.profileForm.controls['birthDate'].setValidators(Validators.required);
    this.profileForm.controls['gender'].setValue(this.profile.gender);
    this.profileForm.controls['gender'][action]();
    this.profileForm.controls['gender'].setValidators(Validators.required);
    this.profileForm.controls['ethnicity'].setValue(this.profile.ethnicity);
    this.profileForm.controls['ethnicity'][action]();
    this.profileForm.controls['height'].setValue(this.profile.height);
    this.profileForm.controls['height'][action]();
    this.profileForm.controls['religion'].setValue(this.profile.religion);
    this.profileForm.controls['religion'][action]();
    this.profileForm.controls['maritalStatus'].setValue(this.profile.maritalStatus);
    this.profileForm.controls['maritalStatus'][action]();
    this.profileForm.controls['maritalStatus'].setValidators(Validators.required);
    this.profileForm.controls['occupation'].setValue(this.profile.occupation);
    this.profileForm.controls['occupation'][action]();
    this.profileForm.controls['occupation'].setValidators(Validators.maxLength(256));
    this.profileForm.controls['figure'].setValue(this.profile.figure);
    this.profileForm.controls['figure'][action]();
    this.profileForm.controls['city'].setValue(this.profile.city.name);
    this.profileForm.controls['city'][action]();
    this.profileForm.controls['city'].setValidators(Validators.required);
    this.profileForm.controls['aboutMe'].setValue(this.profile.about);
    this.profileForm.controls['aboutMe'][action]();
    this.profileForm.controls['aboutMe'].setValidators(Validators.maxLength(5000));

    this.profileForm.controls['city'].valueChanges
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

  private updateProfile(): ProfileTo {
    this.profile.displayName = this.profileForm.controls['displayName'].value;
    this.profile.realName = this.profileForm.controls['realName'].value;
    this.profile.gender = this.profileForm.controls['gender'].value;
    this.profile.city = this.selectedCity;
    this.profile.about = this.profileForm.controls['aboutMe'].value;
    this.profile.birthDate = this.profileForm.controls['birthDate'].value;
    this.profile.ethnicity = this.profileForm.controls['ethnicity'].value;
    this.profile.height = this.profileForm.controls['height'].value;
    this.profile.figure = this.profileForm.controls['figure'].value;
    this.profile.religion = this.profileForm.controls['religion'].value;
    this.profile.maritalStatus = this.profileForm.controls['maritalStatus'].value;
    this.profile.occupation = this.profileForm.controls['occupation'].value;
    return this.profile;
  }

  onFileChange(event) {
    const files = event.target.files;
    if (files && files.length > 0) {
      const file = files[0];
      if (file.size > 10485760) {
        this.notificationService
          .displayError(new Error(`Current image of size ${file.size / 1024 / 1024}MB is too large`));
        return;
      }
      this.notificationService.displayInfo(`Uploading avatar...`);
      this.profileService.uploadAvatar(this.profileId, file)
        .subscribe(base64 => {
          this.profilePicture = base64;
          this.notificationService.displayInfo(`Avatar uploaded successfully!`);
        });
    }
  }

  ngOnDestroy() {
    this.destroy$.next(true);
  }

}
