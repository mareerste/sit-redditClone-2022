import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { UserMenuComponent } from './user-menu/user-menu.component';
import { LoginComponent } from './login/login.component';
import { SignUpComponent } from './sign-up/sign-up.component';

import {AngularMaterialModule} from './angular-material/angular-material.module';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {ApiService} from './service/api.service';
import {PostService} from './service/post.service';
import {AuthService} from './service/auth.service';
import {UserService} from './service/user.service';
import {ConfigService} from './service/config.service';
import { ReactionService } from './service/reaction.service';


import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './interceptor/TokenInterceptor';
import { MainNavComponent } from './main-nav/main-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MainPageComponent } from './pages/main-page/main-page.component';
import { PostComponent } from './components/post/post.component';
import { PostListComponent } from './components/post-list/post-list.component';
import { CommentListComponent } from './components/comment/comment-list/comment-list.component';
import { CommentItemComponent } from './components/comment/comment-item/comment-item.component';
import { NavSearchComponent } from './nav-search/nav-search.component';

@NgModule({
  declarations: [
    AppComponent,
    UserMenuComponent,
    LoginComponent,
    SignUpComponent,
    MainNavComponent,
    MainPageComponent,
    PostComponent,
    PostListComponent,
    CommentListComponent,
    CommentItemComponent,
    NavSearchComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    NoopAnimationsModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
  ],
  providers: [ 
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    PostService,
    AuthService,
    ApiService,
    UserService,
    ConfigService,
    ReactionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
