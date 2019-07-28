import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Patient } from '../shared/intefaces/patient';
import { PatientService } from '../shared/services/patient/patient.service';


@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css']
})
export class AddPatientComponent implements OnInit {
  patientForm: FormGroup;
  nameError: Boolean;
  response: string;

  constructor(private formBuilder: FormBuilder, private patientService: PatientService) {
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
        } catch (err) {
          console.log(err);
        }
      },
      (err) => {

      },
      () => {

      });

  }

  async nameValidation() {
    const nameField = this.patientForm.get('name');
    const nameElement = document.getElementById('name');
    if (nameField.valid) {
      this.nameError = false;
      nameElement.classList.remove('is-invalid');
    } else {
      this.nameError = true;
      nameElement.classList.add('is-invalid');
    }
  }

}
