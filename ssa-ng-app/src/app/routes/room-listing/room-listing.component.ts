import { Component, OnInit } from '@angular/core';
import { RoomService } from 'src/app/shared/services/room/room.service';
import { ToastService } from 'src/app/shared/services/toast/toast.service';

@Component({
  selector: 'app-room-listing',
  templateUrl: './room-listing.component.html',
  styleUrls: ['./room-listing.component.css']
})
export class RoomListingComponent implements OnInit {

  roomList: any;
  responseMessage: any;
  constructor(private roomService: RoomService, private toastService: ToastService) { }

  ngOnInit() {
    const room$ = this.roomService.getAllRooms();
    room$.subscribe((res: any) => {
      try {
        // check data obect in response
        if ('data' in res) {
          this.roomList = res.data;
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
        this.toastService.showError('Unable to fetch rooms.');
      }
    }, (err) => {
      this.toastService.showError('Unable to fetch rooms.');
    }, () => {
    });
  }
}
