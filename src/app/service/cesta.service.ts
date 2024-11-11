import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CestaService {
  private storageKey = 'cestaItems';

  constructor() { }

  addItem(item: any) {
    const items = this.getItems();
    items.push(item);
    this.saveItems(items);
  }

  removeItem(index: number) {
    const items = this.getItems();
    items.splice(index, 1);
    this.saveItems(items);
  }

  getItems(): any[] {
    const items = localStorage.getItem(this.storageKey);
    return items ? JSON.parse(items) : [];
  }

  clearCesta() {
    localStorage.removeItem(this.storageKey);
  }

  private saveItems(items: any[]) {
    localStorage.setItem(this.storageKey, JSON.stringify(items));
  }
}