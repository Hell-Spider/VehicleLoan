import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {UserService} from '../user.service';

import {User} from '../User'
import { UserApplyService } from '../user-apply.service';


@Component({
  selector: 'app-apply-loan',
  templateUrl: './apply-loan.component.html',
  styleUrls: ['./apply-loan.component.css']
})
export class ApplyLoanComponent implements OnInit {
  step1:boolean=true;
  step2:boolean = false;
  step3:boolean = false;
  step4:boolean = false;
  check1:boolean = false;
  check2:boolean = false;
  check3:boolean = false;
  check4:boolean = false;
  show1:String = 'hidden';
  show2:String = 'hidden';
  show3:String = 'hidden';

  onStep1(){
    this.step1 = false;
    this.step2 = true;
    this.check1 = true;
    this.show1 = 'visible';
  }

  onStep2()
  {
    this.step2 = false;
    this.step3= true;
    this.check2 = true;
    this.show2 = 'visible';
  }
  onStep2Prev()

  {
    this.step1 = true;
    this.step2 = false;
   
  }
  onStep3()
  {
    this.step3 = false;
    this.step4 = true;
    this.check3 = true;
    this.show3 = 'visible';
  }
  onStep3Prev()
  {
    this.step2 = true;
    this.step3 = false; 
  }

  onStep4Prev()
  {
    this.step3 = true;
    this.step4=false;
    
  }
  applyForm:FormGroup
  documentDetails:FormGroup
  vehicleDetails:FormGroup
  loanDetails:FormGroup


  first:string ;
  users:User[];


  constructor(private fb: FormBuilder , private applyService: UserApplyService , private service:UserService) { }

  ngOnInit(): void {
    this.service.getAllUsers().subscribe(data=>{
      this.users = data;
    });

   

    
 
    
    this.applyForm = this.fb.group({  
      first:[''],
      last:[''],
      email:[''],
      mobile:[''],
      emi:['',Validators.required],
      address:['',Validators.required],
      district:['',Validators.required],
      state:['',Validators.required],
      pin:['',Validators.required],
      annual:['',Validators.required],
      empType:['',Validators.required],
      
    })
    this.documentDetails = this.fb.group({
      aadhar:['',Validators.required],
      pan:['',Validators.required],
      addressProof:['',Validators.required],
      salary:['',Validators.required],

    })

    this.vehicleDetails = this.fb.group({
      chasis:['',Validators.required],
      brand:['',Validators.required],
      model:['',Validators.required],
      exPrice:['',Validators.required],
      type:['',Validators.required],
      onRoad:['',Validators.required],
    
    })

    this.loanDetails = this.fb.group({
       loanAmount:['',Validators.required],
       loanTenure:['',Validators.required],
       interest:['',Validators.required],
       date:['',Validators.required],
    })




 

  }





}