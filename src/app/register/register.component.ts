import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {UserService} from '../user.service';
import {User} from '../User'


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

 registerForm:FormGroup;
users:User[];

  success:boolean =false;
  fail:boolean = false;
  constructor(private fb: FormBuilder ,private service:UserService) { }

  ngOnInit(): void {

    this.registerForm = this.fb.group({
     firstName:['',Validators.required],
     lastName:['',Validators.required],
     age:['',Validators.required],
     email:['',Validators.required],
     password:['',Validators.required],
     mobile:['',Validators.required],
     gender:['',Validators.required],
    
     
    });


    this.service.getAllUsers().subscribe(data=>{
      this.users = data;
    });


  }


  onRegister()
  {
     

     let u:User  = new User(this.registerForm.controls.email.value, this.registerForm.controls.firstName.value,
      this.registerForm.controls.gender.value, this.registerForm.controls.mobile.value,
      this.registerForm.controls.age.value,this.registerForm.controls.password.value);

     for(let u1 of this.users)
     {
       if(u.email == u1.email)
       {
       this.fail = true;
       this.success= false;
  
       }
     }

     if(this.fail)
     {
      alert("email already registered");
      
     
     }
     else{
       this.success = true;
      this.service.addUser(u).subscribe(data=> this.users.push(u));
      this.fail = false; 
      alert("successful")



     }


     
  }



}
