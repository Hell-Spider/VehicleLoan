import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-emicalculate',
  templateUrl: './emicalculate.component.html',
  styleUrls: ['./emicalculate.component.css']
})
export class EMIcalculateComponent implements OnInit {

display1:number=0 ;
display2:number =0;
display3:number=0;
value:number=0;
show:boolean = false;

  valueChanged1(e) {
    this.display1 = e;
    
}

valueChanged2(e) {
  this.display2 = e;
}

valueChanged3(e) {
  this.display3 = e;
}

calculate()
{
  this.value = Number(this.display1)*Number(this.display2);
  this.show = true;
}


  constructor() { }
 

  ngOnInit(): void {
  }

  


}
