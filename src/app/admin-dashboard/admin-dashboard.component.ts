import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';
import {User} from '../User'

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  
  users:User[];

  constructor(private service:UserService ) { }

  ngOnInit(): void {
    this.service.getAllUsers().subscribe(data=>{
      this.users = data;
    });

  }

}
