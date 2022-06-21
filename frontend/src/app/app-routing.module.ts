import { SuspendedCommunityGuardGuard } from './guards/suspended-community-guard.guard';
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
import { CommunityUpdatePostComponent } from './pages/community/single-community/community-update-post/community-update-post.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';

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
    component:SinglePostComponent,
  },
  {
    path:'community/:id/posts',
    component:SingleCommunityComponent,
    canActivate:[SuspendedCommunityGuardGuard]
  },
  {
    path:'community/:id/create',
    component:CommunityCreatePostComponent,
    canActivate: [
      LoggedInAuthGuardGuard,
      SuspendedCommunityGuardGuard
    ]
  },
  {
    path:'community/create',
    component:CommunityCreateComponent,
    canActivate: [LoggedInAuthGuardGuard]
  },
  {
    path:'post/:id/edit',
    component:CommunityUpdatePostComponent,
  },
  {
    path:'user/:username',
    component:UserProfileComponent,
    canActivate: [LoggedInAuthGuardGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
