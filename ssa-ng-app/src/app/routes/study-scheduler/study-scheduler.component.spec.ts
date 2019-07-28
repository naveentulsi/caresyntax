import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudySchedulerComponent } from './study-scheduler.component';

describe('StudySchedulerComponent', () => {
  let component: StudySchedulerComponent;
  let fixture: ComponentFixture<StudySchedulerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudySchedulerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudySchedulerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
