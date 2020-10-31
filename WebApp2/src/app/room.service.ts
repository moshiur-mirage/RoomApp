import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Room } from './room';

const headerOption = {
  headers: new HttpHeaders({
    'content-type': 'application/json',
  }),
};

@Injectable({
  providedIn: 'root',
})
export class RoomService {
  public baseUrl = 'http://localhost:8090/roomcrudwar/room';
 

  currentRoom: Room = {
    id: null,
    type: '',
    size: null
    
  };

  constructor(private http: HttpClient) {}

  private _refreshNeeded$ = new Subject<void>();

  get refreshNeeded$() {
    return this._refreshNeeded$;
  }

  getAllRoom(): Observable<Room[]> {
    return this.http.get<Room[]>(this.baseUrl, headerOption);
  }

  createRoom(room: Room): Observable<Room> {
    return this.http.post<Room>(this.baseUrl, room, headerOption).pipe(
      tap(() => {
        this._refreshNeeded$.next();
      })
    );
  }

  // createRoom(room: object): Observable<object> {  
  //   return this.http.post(`${this.baseUrl2}`, room,headerOption),pipe;  
  // }

  updateRoom(room: Room): Observable<Room> {
    return this.http.put<Room>(this.baseUrl + '/' + room.id, room).pipe(
      tap(() => {
        this._refreshNeeded$.next();
      })
    );
  }

  deleteRoom(id: number): Observable<Room> {
    return this.http.delete<Room>(this.baseUrl + '/' + id);
  }

  getRoomById(id: number): Observable<Room> {
    return this.http.get<Room>(this.baseUrl + '/' + id, headerOption);
  }

  getRoomBySize(size: string): Observable<Room> {
    return this.http.get<Room>(
      this.baseUrl + '/' + 'size' + size,
      headerOption
    );
  }

  getRoomByType(type: string): Observable<Room> {
    return this.http.get<Room>(
      this.baseUrl + '/' + 'type' + type,
      headerOption
    );
  }
}
