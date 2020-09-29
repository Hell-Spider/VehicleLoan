import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {UserApply} from './UserApply';

@Injectable({
  providedIn: 'root'
})
export class UserApplyService {

  constructor(private http:HttpClient) { }


  addUser(user:UserApply):Observable<Object>{
   
    return this.http.post("http://localhost:3000/applyUsers" , user);
   }
 
   getAllUsers():Observable<UserApply[]>{
   
     return this.http.get<UserApply[]>("http://localhost:3000/applyUsers"); 
   }





}
