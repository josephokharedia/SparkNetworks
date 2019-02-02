export class SingleValueTo {
  id: number;
  name: string;

  constructor(id: number, name: string) {
    this.id = id;
    this.name = name;
  }
}

export class CityTo extends SingleValueTo {
  longitude: string;
  latitude: string;
}

export class ProfileTo {
  id: number;
  displayName: string;
  realName: string;
  birthDate: Date;
  height: number;
  occupation: string;
  about: string;
  figure: SingleValueTo;
  gender: SingleValueTo;
  religion: SingleValueTo;
  maritalStatus: SingleValueTo;
  ethnicity: SingleValueTo;
  city: CityTo;
}

export enum AppErrorType {
  OFFLINE = "OFFLINE",
  TECHNICAL = "TECHNICAL"
}

export class AppError {
  type: AppErrorType;
  message: string;

  constructor(message: string, type = AppErrorType.TECHNICAL) {
    this.message = message;
    this.type = type;
  }
}
