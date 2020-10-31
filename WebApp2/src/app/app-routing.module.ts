import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoomListComponent } from './room-list/room-list.component';
import { RoomsComponent } from './rooms/rooms.component';

const routes: Routes = [
  { path: '', redirectTo: 'room-list', pathMatch: 'full' },
  { path: 'room-list', component: RoomListComponent },
  { path: 'add-room', component:  RoomsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
