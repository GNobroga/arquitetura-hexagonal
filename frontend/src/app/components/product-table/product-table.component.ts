import { ChangeDetectionStrategy, Component, Input, OnDestroy, effect, inject, signal } from '@angular/core';
import { Product } from '../../models/product';
import { FormsModule, NgModel } from '@angular/forms';
import { NgbHighlight, NgbModal, NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { CurrencyPipe } from '@angular/common';
import { ConfirmDeletionComponent } from '../confirm-deletion/confirm-deletion.component';
import { Subscription } from 'rxjs';
import DeleteProductByIdUseCase from '../../usecases/delete-product-by-id.use-case';
import { CreateProductComponent } from '../create-product/create-product.component';
import FindAllProductsUseCase from '../../usecases/find-all-products.use-case';

@Component({
  selector: 'app-product-table',
  standalone: true,
  imports: [FormsModule, NgbHighlight, NgbPaginationModule, CurrencyPipe],
  templateUrl: './product-table.component.html',
  styleUrl: './product-table.component.scss',
})
export class ProductTableComponent implements OnDestroy {

  @Input()
  products: Product[] = [];

  sort = signal('asc');

  search = signal('');

  currentPage = signal(0);

  #modalService = inject(NgbModal);

  subscriptions = [] as Subscription[];

  deleteProductByIdUseCase = inject(DeleteProductByIdUseCase);

  findAllProductsUseCase = inject(FindAllProductsUseCase);

  constructor() {
    effect(() => {
      this.findAllProductsUseCase.execute({ page: ''+this.currentPage(), size: ''+4, term: this.search(), sort: this.sort() as 'asc' | 'desc' })
    })
  }

  getStatus(status: boolean) {
    return status ? 'Disponível' : 'Esgotado';
  }

  confirmDeletion(id: number) {
    const subscription = this.#modalService.open(ConfirmDeletionComponent).closed.subscribe(
        () => this.deleteProductByIdUseCase.execute(id));
    this.subscriptions.push(subscription);
  }

  create() {
    this.#modalService.open(CreateProductComponent);
  }

  changeSort() {
    this.sort.set(this.sort() === 'asc' ? 'desc' : 'asc');
  }

  nextPage() {
    this.currentPage.update(old => old + 1);
  }

  previousPage() {
    this.currentPage.set(Math.min(this.currentPage(), 0));
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}
