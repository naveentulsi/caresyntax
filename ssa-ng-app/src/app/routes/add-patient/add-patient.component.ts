import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Patient } from '../../shared/interfaces/patient/patient';
import { PatientService } from '../../shared/services/patient/patient.service';
import { ToastService } from '../../shared/services/toast/toast.service';


@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css']
})
export class AddPatientComponent implements OnInit {
  patientForm: FormGroup;
  nameError: Boolean;
  responseMessage: string;
  ERROR_MESSAGE = 'Unable to add patients now';

  constructor(private formBuilder: FormBuilder, private patientService: PatientService, private toastService: ToastService) {
    this.nameError = false;
  }

  ngOnInit() {
    this.patientForm = this.formBuilder.group({
      name: ['', [
        Validators.required
      ]],
      sex: '',
      dayOfBirth: ''
    });
  }

  async addPatient() {
    const patientData = this.patientForm.value;
    const patientDateJson: Patient = {
      dayOfBirth: patientData.dayOfBirth,
      patientName: patientData.name,
      patientGender: patientData.sex
    };
    this.patientService.addPatient(patientDateJson).subscribe(
      (res: any) => {
        try {
          // get response message
          if ('message' in res) {
            this.responseMessage = res.message;
            if ('error' in res) {
              this.toastService.showError(this.responseMessage);
            } else {
              this.toastService.showsSuccess(this.responseMessage);
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

  resetForm() {
    this.patientForm.reset();
  }

}
