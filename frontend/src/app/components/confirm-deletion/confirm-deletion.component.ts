import { Component, inject } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-confirm-deletion',
  standalone: true,
  imports: [],
  templateUrl: './confirm-deletion.component.html',
  styleUrl: './confirm-deletion.component.scss'
})
export class ConfirmDeletionComponent {
  modal = inject(NgbActiveModal);

  close() {
    this.modal.close(true);
  }
}
