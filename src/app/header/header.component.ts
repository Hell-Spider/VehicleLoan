import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { UserService } from '../user.service';
import { HostListener } from '@angular/core';



@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  scrollHead = false;
  showDrop:boolean = false;
  list:boolean = false;
  showLogin = true;
  link="user-login";
  
  @HostListener("document:scroll")
  scrollFunction()
  {
    if(document.body.scrollTop>20 || document.documentElement.scrollTop>20)
    {
this.scrollHead = true;
    }
    else{
this.scrollHead = false;
    }
  }
  

  constructor(public service:UserService  )
  { 
    
  }

 name:String ="Login";
  
  ngOnInit(): void {
    if(localStorage.getItem("loginName")!=null)
    {
     this.name = localStorage.getItem("loginName");
    this.showDrop=true;
     this.showLogin=false;
     this.link = "apply-loan";
   
    }



  }
    
  
logOut()
{
  
  localStorage.removeItem("loginName")
  this.showLogin=true;
  this.showDrop = false;
 
  
}


loginList()
{
  this.list = true;
}



 

}
