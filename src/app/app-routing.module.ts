import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { ApplyLoanComponent } from './apply-loan/apply-loan.component';
import { DocumentationComponent } from './documentation/documentation.component';
import { EligibilityComponent } from './eligibility/eligibility.component';
import { EMIcalculateComponent } from './emicalculate/emicalculate.component';
import { LoginComponent } from './login/login.component';
import { MainPageComponent } from './main-page/main-page.component';
import { RegisterComponent } from './register/register.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { UserLoginComponent } from './user-login/user-login.component';

const routes: Routes = [
  {path:'',component:MainPageComponent},
  {path:'app-login', component:LoginComponent},
  {path:'user-login', component:UserLoginComponent},
  {path:'register' , component:RegisterComponent},
  {path:'admin-login' ,component:AdminLoginComponent},
  {path:'eligibility', component:EligibilityComponent},
  {path:'apply-loan',component:ApplyLoanComponent},
  {path:'emi-calculator', component:EMIcalculateComponent},
  {path:'userDashboard', component:UserDashboardComponent},
  {path:'adminDashboard', component:AdminDashboardComponent},
  {path:'document' , component:DocumentationComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
