import { EventEmitter, Injectable } from '@angular/core';
import {User} from './User'
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class UserService {




  constructor(private http:HttpClient ) 
  {
    
  }
 
  

  

  
 

  



  addUser(user:User):Observable<Object>{
   
   return this.http.post("http://localhost:3000/users" , user);
  }

  getAllUsers():Observable<User[]>{
  
    return this.http.get<User[]>("http://localhost:3000/users");
  }

}
