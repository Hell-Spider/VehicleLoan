export class UserApply{
    address:string;
    state:string;
    city:string;
    pin:string;
    emptype:string;
    salary:string;
    aadhar:string;
    pan:string;
    salarySlip:string;
    addressProof:string;
   

    constructor( address:string,
        state:string,
        city:string,
        pin:string,
        emptype:string,
        salary:string,
        aadhar:string,
        pan:string,
        salarySlip:string,
        addressProof:string,
        )
        {
            this.address=address;
            this.state = state;
            this.city=city;
            this.emptype = emptype;
            this.salary = salary;
            this.aadhar = aadhar;
            this.pan=pan;
            this.salarySlip=salarySlip;
            this.addressProof=addressProof;
           
        }

}