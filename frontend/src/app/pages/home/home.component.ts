import { ChangeDetectionStrategy, Component, OnInit, inject } from '@angular/core';
import { ProductTableComponent } from '../../components/product-table/product-table.component';
import BaseStore from '../../store/base.store';
import { Product } from '../../models/product';
import FindAllProductsUseCase from '../../usecases/find-all-products.use-case';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ProductTableComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent extends BaseStore<Product> implements OnInit {

  findAllProductsUseCase = inject(FindAllProductsUseCase);

  ngOnInit(): void {
    this.findAllProductsUseCase.execute({ size: ''+4, });
  }



}
