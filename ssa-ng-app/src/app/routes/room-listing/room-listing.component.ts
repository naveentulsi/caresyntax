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
  constructor(private roomService: RoomService, private toastService: ToastService) { }

  ngOnInit() {
    const rooms$ = this.roomService.getAllRooms();
    rooms$.subscribe((res: any) => {
      try {
        this.roomList = res.message;
      } catch (err) {
        this.showError(err);
      }
    }, (err) => {
      this.showError(err);
    }, () => {
    });
  }

  /**
   * Shoots error toast message
   * @param err error object to logged
   */
  private showError(err: any) {
    this.toastService.showError('Not able to fetch rooms.');
    console.log(err);
  }

}
