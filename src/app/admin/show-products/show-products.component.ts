import { environment } from './../../../environments/environment';
import { Product } from './../../Model/product';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-show-products',
  templateUrl: './show-products.component.html',
  styleUrls: ['./show-products.component.css']
})
export class ShowProductsComponent {

  constructor(private http:HttpClient){ }
  allProduct: Product[] = [];

  ngOnInit(): void
  {
    //Server call To Get All Products
    this.http.get(environment.url+'getAllProducts').subscribe(
      (data:any)=>{
        if(data==null)
        {
          window.alert("Something is Wrong");
        }
        else
        {
          this.allProduct=data;
        }
      }
    );
  }
}
