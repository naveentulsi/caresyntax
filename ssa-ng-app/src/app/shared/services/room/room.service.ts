import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UtilService } from '../util/util.service';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  endpoint: string = UtilService.getServerUrl().concat('room');
  constructor(private httpCliet: HttpClient) { }

  public getAllRooms() {
    try {
      return this.httpCliet.get(this.endpoint);
    } catch (e) {
      return;
    }
  }
}
