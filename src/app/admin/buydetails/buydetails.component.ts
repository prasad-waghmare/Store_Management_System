import { Specificbuyproducts } from './../../Model/specificbuyproducts';
import { environment } from './../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-buydetails',
  templateUrl: './buydetails.component.html',
  styleUrls: ['./buydetails.component.css']
})
export class BuydetailsComponent {

  constructor(private http:HttpClient){ }

  allBuyProductList:Specificbuyproducts[]=[];

  ngOnInit(){
    //Server Call to Get Buy Details
    this.http.get(environment.url+'getAllBuyProductData').subscribe(
      (data:any)=>
      {
        if(data==null)
        {
          window.alert("Something is Wrong");
        }
        else
        {
          this.allBuyProductList=data;
        }
      }
    );
  }

}
