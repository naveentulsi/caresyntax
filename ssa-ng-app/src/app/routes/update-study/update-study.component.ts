import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ScheduleService } from 'src/app/shared/services/schedule/schedule.service';
import { ToastService } from 'src/app/shared/services/toast/toast.service';
import { ProcedureUpdate } from 'src/app/shared/interfaces/procedure/procedure';

@Component({
  selector: 'app-update-study',
  templateUrl: './update-study.component.html',
  styleUrls: ['./update-study.component.css']
})
export class UpdateStudyComponent implements OnInit {
  procedureList: any[];
  responseMessage: string;
  statusUpdationMap: Map<number, string>;

  constructor(private scheduleService: ScheduleService, private toastService: ToastService, private changeRef: ChangeDetectorRef) {
    this.statusUpdationMap = new Map();
  }

  ngOnInit() {
    const procedure$ = this.scheduleService.getAllProcedure();
    procedure$.subscribe((res: any) => {
      try {
        // check data obect in response
        if ('data' in res) {
          this.procedureList = res.data;
          this.changeRef.detectChanges();
        }
        // get response message
        if ('message' in res) {
          this.responseMessage = res.message;
        }
        // check errro obect in response
        if ('error' in res) {
          this.toastService.showError(this.responseMessage);
        }
      } catch (err) {
        this.toastService.showError('Unable fetch schedules now.');
      }
    }, (err) => {
      console.log(err);
      this.toastService.showError('Unable fetch schedules now.');
    }, () => {
    });
  }

  public updateStatus(selectedStatus: any, procedureId: any) {
    this.statusUpdationMap.set(procedureId, selectedStatus);
    console.log(selectedStatus);
    console.log(procedureId);
  }

  public update(previousStatus: any, procedureId: any) {

    const updatedStatus = this.statusUpdationMap.get(procedureId);
    if (previousStatus !== updatedStatus) {
      console.log(previousStatus);
      const procedure: ProcedureUpdate = {
        id: procedureId,
        status: updatedStatus
      };

      const updateProcedure$ = this.scheduleService.updateProcedureStatus(procedure);
      updateProcedure$.subscribe((res: any) => {
        try {
          // get response message
          if ('message' in res) {
            this.responseMessage = res.message;
          }
          // check errro obect in response
          if ('error' in res) {
            this.toastService.showError(this.responseMessage);
          } else {
            this.toastService.showInfo(this.responseMessage, 'Status Update');
          }
        } catch (err) {
          this.toastService.showError('Unable to update schedule now.');
        }
      }, (err) => {
        console.log(err);
        this.toastService.showError('Unable to update schedule now.');
      });
    } else {
      this.toastService.showError('Please update the status value to trigger an update.');
    }
  }

}
