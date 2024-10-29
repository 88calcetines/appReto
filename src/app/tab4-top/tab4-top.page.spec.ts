import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Tab4TopPage } from './tab4-top.page';

describe('Tab4TopPage', () => {
  let component: Tab4TopPage;
  let fixture: ComponentFixture<Tab4TopPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(Tab4TopPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
