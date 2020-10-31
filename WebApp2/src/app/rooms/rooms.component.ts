import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Room } from '../room';
import { RoomService } from '../room.service';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css'],
})
export class RoomsComponent implements OnInit {
  selectRoomId: number = null;
  constructor(public roomService: RoomService) {}

  selectChange(event: any) {
    this.selectRoomId = event.target.value;
  }
  ngOnInit() {}

  createOrUpdate(currentRoom: Room) {
    if (currentRoom.id != null) {
      this.updateRoom(currentRoom);
    } else {
      this.createRoom(currentRoom);
    }
  }

  updateRoom(currentRoom: Room) {
    this.roomService.updateRoom(currentRoom).subscribe();
  }

  createRoom(currentRoom: Room) {
    this.roomService.createRoom(currentRoom).subscribe();
    this.ngOnInit();
  }

  clear() {
    this.roomService.currentRoom = {
      id: null,
      roomNo: '',
      type: '',
      size: '',
      rent: null,
    };
  }
}
