import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateStudyComponent } from './update-study.component';

describe('UpdateStudyComponent', () => {
  let component: UpdateStudyComponent;
  let fixture: ComponentFixture<UpdateStudyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateStudyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateStudyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
