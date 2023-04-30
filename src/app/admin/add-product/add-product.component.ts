import { Product } from './../../Model/product';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent {

  constructor(private http:HttpClient){ }

  product={} as Product;

  addProduct(){
    console.log(this.product)
    //Server call for add Product
    this.http.post('http://localhost:8080/addProduct',this.product).subscribe(
      (data:any)=>{
        if(!data)
        {
          window.alert("Product Not Added")
        }
        else
        {
          window.alert("Added Successfully");
          //this.product=new this.product;
        }

      }
    );
  }
}
