import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UtilService } from '../util/util.service';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private httpCliet: HttpClient) { }

  public getAllRooms() {
    const serverUrl = UtilService.getServerUrl();
    const getRoomsEndpoint = serverUrl.concat('rm');
    return this.httpCliet.get(getRoomsEndpoint);
  }
}
