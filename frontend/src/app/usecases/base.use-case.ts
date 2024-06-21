import { HttpClient } from "@angular/common/http";
import { inject } from "@angular/core";
import { environment } from "../../environments/environment";

export default class BaseUseCase {
  protected http = inject(HttpClient);
  protected baseUrl!: string;

  constructor(urlPrefix: string) {
    this.baseUrl = `${environment.apiUrl}/${urlPrefix}`;
  }

  protected urlWith(path: string) {
    return this.baseUrl + path;
  }
}
