import { HttpClient } from "@angular/common/http";
import { Injectable, inject, signal } from "@angular/core";
import { Product } from "../models/product";
import { environment } from "../../environments/environment";
import { ObjectUnsubscribedError, first, tap } from "rxjs";
import BaseUseCase from "./base.use-case";

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
export default class FindAllProductsUseCase extends BaseUseCase {

  productsSignal = signal([] as Product[]);

  constructor() {
    super('products');
  }

  execute(criteria?: Partial<SearchCriteria>) {
    console.log(this.#convertToParams(criteria!))
    this.http.get<APIResponse<Product>>(this.urlWith(`${this.#convertToParams(criteria!)}`))
      .pipe(first(), tap(result => this.productsSignal.set(result.items))).subscribe();
  }

  #convertToParams(criteria: Partial<SearchCriteria>) {
    if (!criteria) return '';
    let params = Object.keys(criteria).length ? '?' : '';
    for (const [param, value] of Object.entries(criteria)) {
      if (value && value.trim().length) {
        params += `${param}=${value}&`;
      }
    }
    return params;
  }
}
