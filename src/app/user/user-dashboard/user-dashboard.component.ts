import { Product } from './../../Model/product';
import { environment } from './../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { MyuserService } from './../../services/myuser.service';

import { Component } from '@angular/core';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent {

  constructor(private http:HttpClient, public service:MyuserService){}

  isShow=0;
  allProducts:Product[]=[];

  getAllProduct(num:number)
  {
    //Server Call to get All product
    this.http.get(environment.url+'getAvailableProd').subscribe(
      (data:any)=>
      {
        if(data==null)
        {
          window.alert("Products Not Available")
        }
        else
        {
          this.isShow=num;
          this.allProducts=data;
        }
      }
    );
  }

  Refresh()
  {
    this.getAllProduct(1);
  }

  buy(UIQuantity:any,prodQuantity:number,productId:number)
  {
    let userQuantity=parseInt(UIQuantity);
    if(userQuantity>prodQuantity)
    {
      window.alert("Enter Valid Number !!! Available Quantity "+prodQuantity)
    }
    else
    {
      if(window.confirm("Are You Sure"))
      {
        //Server call for update product quantity
        this.http.get(environment.url+'updateProductQuantity'+productId+'and'+userQuantity).subscribe(
          (data:any)=>{
            if(!data)
            {
              window.alert("Data not Updated")
            }
            else
            {
              //Update current product quantity
              for(let i=0; i<this.allProducts.length; i++)
              {
                if(this.allProducts[i].id==productId)
                {
                  this.allProducts[i].quantity=(this.allProducts[i].quantity-userQuantity);
                  break;
                }
              }
              this.addProductInUserAcc(productId,userQuantity);
            }
          }
        );
      }
    }
  }
  //Add Product in User Accounnt
  addProductInUserAcc(productId:number,userQuantity:number)
  {
    let userId=this.service.getData().id;

    //Server Call to Add Product in User Account
    this.http.get(environment.url+'addProductInUserAcc'+productId+'and'+userId+'and'+userQuantity).subscribe(
    (data:any)=>{
      if(!data)
      {
        window.alert("Data Not Added");
      }
    }
    );
  }
}
