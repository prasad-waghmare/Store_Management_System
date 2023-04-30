import { User } from './../Model/user';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MyuserService {

  constructor() { }

  private user: User;

  setData(user:User)
  {
    this.user=user;
  }

  getData()
  {
    return this.user
  }
}
