import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {UserService} from '../user.service';
import{LoanApplicationService} from '../loan-application.service'

import {User} from '../User'
import { UserApplyService } from '../user-apply.service';
import { UserApply } from '../UserApply';
import{LoanApplication} from '../LoanApplication'


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

  users:User[];

  constructor(private fb: FormBuilder , private applyService: UserApplyService , private service:UserService , private loanService:LoanApplicationService) { }

  ngOnInit(): void {


    this.service.getAllUsers().subscribe(data=>{
      this.users = data;

      for(let u1 of this.users)
      {
        if(u1.name == localStorage.getItem("loginName"))
        {
          this.applyForm.controls.first.setValue(  u1.name)
          this.applyForm.controls.email.setValue(u1.email)
          this.applyForm.controls.mobile.setValue(u1.mobile)
         
        }
      }
     
    });

 
    
    this.applyForm = this.fb.group({  
      first:[''],
      last:[''],
      email:[''],
      mobile:[''],
    
      address:['',Validators.required],
      city:['',Validators.required],
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
      color:['',Validators.required]
    
    })

    this.loanDetails = this.fb.group({
      emi:['',Validators.required],
       loanAmount:['',Validators.required],
       loanTenure:['',Validators.required],
       interest:['',Validators.required],
       date:['',Validators.required],
    })

  }

usersApply:UserApply[];
 u:UserApply;

  onStep2()
  {
    this.step2 = false;
    this.step3= true;
    this.check2 = true;
    this.show2 = 'visible';

    
   this.u = new UserApply(this.applyForm.controls.address.value ,this.applyForm.controls.state.value ,
       this.applyForm.controls.city.value, this.applyForm.controls.pin.value ,
        this.applyForm.controls.empType.value,this.applyForm.controls.annual.value,
         this.documentDetails.controls.aadhar.value, this.documentDetails.controls.pan.value,
         this.documentDetails.controls.salary.value, this.documentDetails.controls.addressProof.value
         )

       
         this.applyService.addApplyUser(this.u).subscribe(data=> this.usersApply.push(this.u));

  }

  loanApp:LoanApplication[]
  

  onStep4()
  {
    
 
 
    

    let u1:LoanApplication = new LoanApplication(this.vehicleDetails.controls.chasis.value ,this.vehicleDetails.controls.brand.value,
      this.vehicleDetails.controls.color.value , this.vehicleDetails.controls.model.value,this.vehicleDetails.controls.type.value,
      this.vehicleDetails.controls.exPrice.value, this.vehicleDetails.controls.onRoad.value,
      this.loanDetails.controls.emi.value, this.loanDetails.controls.loanTenure.value,this.loanDetails.controls.interest.value, 
      this.loanDetails.controls.loanAmount.value, this.u )

  console.log(u1)
    

      this.loanService.addLoanApplication(u1).subscribe(data=> this.loanApp.push(u1));
  }





}