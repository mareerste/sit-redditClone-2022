import { SignUpComponent } from './pages/sign-up/sign-up.component';
import { MainPageComponent } from './pages/main-page/main-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
  path: '',
  component: MainPageComponent
  },
  {
    path:'signup',
    component:SignUpComponent
  },
  {path:'search/:entry',
  component:MainPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations:[]
})
export class AppRoutingModule { }
