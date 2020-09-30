import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';
import {User} from '../User'
import { LoanApplicationService } from '../loan-application.service';
import { LoanApplication } from '../LoanApplication';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  
  users:User[];
  loanUser:LoanApplication[]
  view:boolean = false;

  constructor(private service:UserService , private loanService:LoanApplicationService ) { }

  ngOnInit(): void {
    this.service.getAllUsers().subscribe(data=>{
      this.users = data;
    });
this.loanService.getAllLoanApplication().subscribe(data=>{
this.loanUser = data;
})
  }

displayDetails:User;
loanDetails:LoanApplication;
  viewForm(u)
  {
this.view = true;
this.displayDetails = u;




  }

  close()
  {
    this.view = false;
  }

}
