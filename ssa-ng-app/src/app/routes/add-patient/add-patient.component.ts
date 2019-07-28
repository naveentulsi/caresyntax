import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Patient } from '../../shared/intefaces/patient';
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
  response: string;

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
          this.response = res.message;
          this.toastService.showsSuccess(this.response);
        } catch (err) {
          console.log(err);
        }
      },
      (err) => {
        console.log(err);
        this.toastService.showError('Unable to add patients now.');
      },
      () => {

      });

  }

}
