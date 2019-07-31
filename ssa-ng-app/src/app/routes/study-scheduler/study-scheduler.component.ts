import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Procedure } from 'src/app/shared/interfaces/procedure/procedure';
import { ScheduleService } from 'src/app/shared/services/schedule/schedule.service';
import { ToastService } from 'src/app/shared/services/toast/toast.service';

@Component({
  selector: 'app-study-scheduler',
  templateUrl: './study-scheduler.component.html',
  styleUrls: ['./study-scheduler.component.css']
})
export class StudySchedulerComponent implements OnInit {
  response: any;
  selectedDate: Date;
  studyScheduleForm: FormGroup;
  showResultBlock = false;
  dynamicResultList: any;
  ERROR_MESSAGE = 'Unable to schedule study';
  public minDate: Date = new Date();

  constructor(private formBuilder: FormBuilder, private scheduleService: ScheduleService, private toastService: ToastService) { }

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
    const procedure = this.prepareProcedure();
    this.scheduleService.scheduleProcdure(procedure).subscribe(
      (res: any) => {
        try {
          // get response message
          if ('message' in res) {
            this.response = res.message;
            if ('error' in res) {
              this.toastService.showError(this.response);
            } else {
              this.toastService.showsSuccess(this.response);
            }
          }
          this.resetForm();
        } catch (err) {
          this.toastService.showError(this.ERROR_MESSAGE);
          this.resetForm();
          console.log(err);
        }
      },
      (err) => {
        console.log(err);
        this.resetForm();
        this.toastService.showError(this.ERROR_MESSAGE);
      },
      () => {
        this.resetForm();
      });
  }

  public prepareProcedure(): Procedure {
    const studyScheduleFormData = this.studyScheduleForm.value;
    const procedure: Procedure = {
      status: String(studyScheduleFormData.status),
      plannedEndDate: this.convertStringToDate(studyScheduleFormData.plannedEndDate),
      plannedStartDate: this.convertStringToDate(studyScheduleFormData.plannedStartDate),
      patientId: studyScheduleFormData.patient,
      doctorId: studyScheduleFormData.doctor,
      description: studyScheduleFormData.description
    };
    return procedure;
  }

  public resetForm() {
    this.studyScheduleForm.reset();
  }

  private convertStringToDate(dateInstance: any): string {
    try {
      const date = String(dateInstance.getDate());
      const month = String(dateInstance.getMonth() + 1);
      const year = String(dateInstance.getFullYear());
      if (date && date !== '' && month && month !== '' && year && year !== '') {
        const formattedDate = year.concat('/').concat(month).concat('/').concat(date);
        console.log(formattedDate);
        return formattedDate;
      }
    } catch (e) {
      console.log('Unable to format date.');
    }
    return '';
  }

}
