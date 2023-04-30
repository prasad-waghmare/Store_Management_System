import { MyuserService } from './../../services/myuser.service';
import { AppComponent } from './../../app.component';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent {

  constructor(private http:HttpClient,private appComp:AppComponent,private service:MyuserService){}

  login(username:String,password:String)
  {
    console.log(username+" "+password);

    //server call for Authenticating User
    this.http.get('http://localhost:8080/loginUser'+username+'and'+password).subscribe(
      (data:any)=>{
        if(!data)
        {
          window.alert("Login Not Successful");
        }
        else
        {
          window.alert("Login Successful");

          //Server call to get User Role
          this.http.get('http://localhost:8080/getUserRole'+username).subscribe(
            (data:any)=>{
              if(data==null)
              {
                window.alert("Something is wrong");
              }
              else
              {
                this.appComp.userRole=data.userRole;
                this.service.setData(data);
              }
            }
          );
        }
      }
    );
  }
}
