import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-study-scheduler',
  templateUrl: './study-scheduler.component.html',
  styleUrls: ['./study-scheduler.component.css']
})
export class StudySchedulerComponent implements OnInit {
  selectedDate: Date;
  studyScheduleForm: FormGroup;
  showResultBlock = false;
  dynamicResultList: any;
  public minDate: Date = new Date();

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.showResultBlock = true;
    this.studyScheduleForm = this.formBuilder.group({
      patient: ['', Validators.required],
      doctor: ['', Validators.required],
      plannedStartDate: ['', Validators.required],
      plannedEndDate: '',
      room: '',
      status: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  public schedule() {
    const value = this.studyScheduleForm.value;
    console.log(value);
  }

}
