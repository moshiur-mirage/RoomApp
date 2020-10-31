import { Component, OnInit } from '@angular/core';
import { Room } from '../room';
import { RoomService } from '../room.service';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css'],
})
export class RoomListComponent implements OnInit {
  allRoom: Room[];

  constructor(private roomService: RoomService) {}

  ngOnInit() {
    this.getAllRoom();
    this.roomService.refreshNeeded$.subscribe(() => {
      this.getAllRoom();
    });
    this.getAllRoom();
  }

  getAllRoom() {
    this.roomService.getAllRoom().subscribe((data: Room[]) => {
      this.allRoom = data;
    });
  }

  deleteRoom(custid: number) {
    this.roomService.deleteRoom(custid).subscribe((data: Room) => {
      this.getAllRoom();
    });
  }

  editRoom(room: Room) {
    this.roomService.currentRoom = Object.assign({}, room);
  }
}
