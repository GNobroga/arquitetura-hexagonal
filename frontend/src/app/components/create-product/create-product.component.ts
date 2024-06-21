import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import CreateProductUseCase from '../../usecases/create-product.use-case';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-create-product',
  standalone: true,
  imports: [ReactiveFormsModule, JsonPipe],
  templateUrl: './create-product.component.html',
  styleUrl: './create-product.component.scss'
})
export class CreateProductComponent {
  modal = inject(NgbActiveModal);

  form = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(100)]),
    description: new FormControl('', [Validators.required, Validators.minLength(10), Validators.maxLength(255)]),
    imageUrl: new FormControl('', [Validators.maxLength(100)]),
    price: new FormControl(0, [Validators.required, Validators.min(0)]),
    available: new FormControl(false),
  });

  createProductUseCase = inject(CreateProductUseCase);

  save() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.createProductUseCase.execute(this.form.value as any);
    this.modal.close();
  }

  validate(control: keyof typeof this.form.controls) {
    const formControl = this.form.get(control);
    if (formControl?.hasError('required')) {
      return 'O campo é obrigatório';
    }

    if (formControl?.hasError('maxlength')) {

      const { requiredLength } = formControl.getError('maxlength');
      return `O campo precisa ser menor que ${requiredLength} caracteres.`;
    }

    if (formControl?.hasError('minlength')) {
      const { requiredLength } = formControl.getError('minlength');
      return `O campo precisa ser maior que ${requiredLength} caracteres.`;
    }

    if (formControl?.hasError('min')) {
      const { min } = formControl.getError('min');
      return `O valor mínimo desse campo é ${min}`;
    }

    return '';
  }
}
