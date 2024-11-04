import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Tab2TopPage } from './tab2-top.page';

describe('Tab2TopPage', () => {
  let component: Tab2TopPage;
  let fixture: ComponentFixture<Tab2TopPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(Tab2TopPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
