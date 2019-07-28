import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DyanmicListingComponent } from './dyanmic-listing.component';

describe('DyanmicListingComponent', () => {
  let component: DyanmicListingComponent;
  let fixture: ComponentFixture<DyanmicListingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DyanmicListingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DyanmicListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
