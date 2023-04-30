import { HttpClient, HttpClientModule } from '@angular/common/http';
import { User } from './../../Model/user';
import { Component } from '@angular/core';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent {

  constructor(private http:HttpClient){}

  user={} as User;
  // user1 !:User;    Ways of Creating Object
  //user2=new User;

  registerUsers(username:String,password:String)
  {
    console.log(this.user);
    console.log(username+" "+password);
    //server call
    this.http.post('http://localhost:8080/registerUsers'+username+'and'+password,this.user).subscribe(
      (data:any)=>{
        //response logic
        if(data==false)
        {
          window.alert("Registration Failed");
        }
        else
        {
          window.alert("Registration Successful");
        }
      }
    );
    console.log("Hello");
  }
}
