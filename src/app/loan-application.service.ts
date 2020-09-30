import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoanApplication } from './LoanApplication';

@Injectable({
  providedIn: 'root'
})
export class LoanApplicationService {

  constructor(private http:HttpClient ) 
  {
    
  }
 
  

  addLoanApplication(loan:LoanApplication):Observable<Object>{
   
   return this.http.post("http://localhost:3000/loanApplication" , loan);
  }

  getAllLoanApplication():Observable<LoanApplication[]>{
  
    return this.http.get<LoanApplication[]>("http://localhost:3000/loanApplication");
  }
}
