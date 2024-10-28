import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Tab3TopPage } from './tab3-top.page';

describe('Tab3TopPage', () => {
  let component: Tab3TopPage;
  let fixture: ComponentFixture<Tab3TopPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(Tab3TopPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
