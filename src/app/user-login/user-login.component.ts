import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {UserService} from '../user.service';
import {User} from '../User'
import { Router } from '@angular/router';



@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {



  
  userLoginForm:FormGroup;
 
  exist:boolean = false; 
  name:String ="";
 


  userList:User[];
  constructor(private fb:FormBuilder,public service:UserService , private router:Router) { }
 
  ngOnInit(): void 
  {
      this.userLoginForm = this.fb.group({
        username:['',Validators.required],
        password:['',Validators.required]
      });

      
    this.service.getAllUsers().subscribe(data=>{
      this.userList = data;
    });

  } 

  onLogin()
  {

     let usern = this.userLoginForm.controls.username.value 
     let pass = this.userLoginForm.controls.password.value
    
   
      for(let u of this.userList)
      {
        if(u.email==usern)
        {
          this.exist = true;
          if(u.password==pass)
          {
            this.name = u.name;
           
            localStorage.setItem("loginName" , u.name );
            alert(localStorage.getItem("loginName"))
           
            this.router.navigate(['userDashboard']);
           
            
          }
          else{
            alert("Invalid credentials")
          }
        }

        
      }

if(this.exist==false)

{
  alert("you are not a registered user")
}
  }





}

