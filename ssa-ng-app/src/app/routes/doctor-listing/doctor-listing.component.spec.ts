import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorListingComponent } from './doctor-listing.component';

describe('DoctorListingComponent', () => {
  let component: DoctorListingComponent;
  let fixture: ComponentFixture<DoctorListingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoctorListingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorListingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
