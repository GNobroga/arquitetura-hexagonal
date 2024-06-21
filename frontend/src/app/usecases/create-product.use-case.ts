import { Injectable, inject } from "@angular/core";
import BaseUseCase from "./base.use-case";
import { Product } from "../models/product";
import FindAllProductsUseCase from "./find-all-products.use-case";
import { first, tap } from "rxjs";

@Injectable({
  providedIn: 'root',
})
export default class CreateProductUseCase extends BaseUseCase {

  findAllProductsUseCase = inject(FindAllProductsUseCase);

  constructor() {
    super('products');
  }

  execute(record: Product) {
    this.http.post(this.urlWith(''), record).pipe(first(),
      tap(() => this.findAllProductsUseCase.execute({ size: ''+4}))).subscribe();
  }
}
