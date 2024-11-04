import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Tab1TopPage } from './tab1-top.page';

describe('Tab1TopPage', () => {
  let component: Tab1TopPage;
  let fixture: ComponentFixture<Tab1TopPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(Tab1TopPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
