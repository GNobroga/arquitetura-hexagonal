import { computed, signal } from "@angular/core";

export default class BaseStore<T> {

  protected data = signal<T>({} as T);

  protected select<R>(fnc: (store: T) => R) {
    return computed(() => fnc(this.data()));
  }

  protected setState(state: T) {
    this.data.set(state);
  }

}
