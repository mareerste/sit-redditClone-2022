import { LoggedInAuthGuardGuard } from './guards/logged-in-auth-guard.guard';
import { CommunityCreatePostComponent } from './pages/community/single-community/community-create-post/community-create-post.component';
import { CommunityCreateComponent } from './pages/community/community-create/community-create.component';
import { SingleCommunityComponent } from './pages/community/single-community/single-community/single-community.component';
import { SinglePostComponent } from './pages/single-post/single-post.component';
import { MainPageComponent } from './pages/main-page/main-page.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';

const routes: Routes = [
  {
    path: '',
    component: MainPageComponent,
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'signup',
    component: SignUpComponent,
  },
  {
    path:'post/:id',
    component:SinglePostComponent
  },
  {
    path:'community/:id/posts',
    component:SingleCommunityComponent
  },
  {
    path:'community/:id/create',
    component:CommunityCreatePostComponent,
    canActivate: [LoggedInAuthGuardGuard]
  },
  {
    path:'community/create',
    component:CommunityCreateComponent,
    canActivate: [LoggedInAuthGuardGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
