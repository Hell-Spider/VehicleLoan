import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  
  adminLoginForm:FormGroup;
  constructor( private fb:FormBuilder , private router: Router) { }

  ngOnInit(): void {

    
    this.adminLoginForm = this.fb.group({
      adminUsername:['',Validators.required],
      adminPassword:['',Validators.required]
    });


  }

adminLogin()
{
  this.router.navigate(['/adminDashboard']);
}



}

