export class LoanApplication{
    chassisNo:string;
	existingEMI:number;
	tenure:number;
	interest:number;
	amount:number;
	appdate:Date;
	status:string;
	brand:string;
	colour:string;
	model:string;
	type:number;
	exShowPrice:number;
    onRoadPrice:number;
    
    constructor(chassisNo:string,existingEMI:number, tenure:number, interest:number, amount:number, brand:string, colour:string, model:string, type:number, exShowPrice:number,
        onRoadPrice:number) {
    this.chassisNo = chassisNo,
    this.existingEMI = existingEMI;
    this.tenure = tenure;
    this.interest = interest;
    this.amount = amount;
    this.brand = brand;
    this.colour = colour;
    this.model = model;
    this.type = type;
    this.exShowPrice = exShowPrice;
    this.onRoadPrice = onRoadPrice;
}
}
