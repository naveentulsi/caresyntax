import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(private toastr: ToastrService) { }

  showsSuccess(message: string) {
    this.toastr.success(message, 'Success');
  }

  showError(errorMessage: string) {
    this.toastr.error(errorMessage, 'Error');
  }

  showInfo(info: string, header: string) {
    this.toastr.info(info, header);
  }
}
