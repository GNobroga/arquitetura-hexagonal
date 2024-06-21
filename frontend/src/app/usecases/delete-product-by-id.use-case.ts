import { HttpClient } from "@angular/common/http";
import { Injectable, inject, signal } from "@angular/core";
import { Product } from "../models/product";
import { environment } from "../../environments/environment";
import { ObjectUnsubscribedError, first, switchMap, tap } from "rxjs";
import BaseUseCase from "./base.use-case";
import FindAllProductsUseCase from "./find-all-products.use-case";

interface APIResponse<T> {
  size: number;
  page: number;
  totalElements: number;
  items: T[];
}
export interface SearchCriteria {
  size: string;
  page: string;
  sort: 'asc' | 'desc';
  term: string;
}

@Injectable({
  providedIn: 'root',
})
export default class DeleteProductByIdUseCase extends BaseUseCase {

  findAllProductsUseCase = inject(FindAllProductsUseCase);

  constructor() {
    super('products');
  }

  execute(id: number) {
    this.http.delete(this.urlWith('/' + id.toString()))
      .pipe(first(), tap(() => this.findAllProductsUseCase.execute({ size: ''+4}))).subscribe();
  }

}
