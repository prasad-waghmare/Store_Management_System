import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AddProductComponent } from './add-product/add-product.component';
import { ShowProductsComponent } from './show-products/show-products.component';
import { BuydetailsComponent } from './buydetails/buydetails.component';



@NgModule({
  declarations: [
    AdminDashboardComponent,
    AddProductComponent,
    ShowProductsComponent,
    BuydetailsComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports:[
    AdminDashboardComponent,
    AddProductComponent,
    ShowProductsComponent
  ]
})
export class AdminModule { }
