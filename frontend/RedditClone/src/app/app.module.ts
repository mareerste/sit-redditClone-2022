import { NgModule, Component } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MainNavComponent } from './main-nav/main-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { NavSearchComponent } from './nav-search/nav-search.component';
import { FormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import { PostListComponent } from './main/post/post-list/post-list.component';
import { RouterModule, Routes } from '@angular/router';
import { MainPageComponent } from './pages/main-page/main-page.component';
import { HttpClientModule } from '@angular/common/http';
import { ApiService } from './services/api.service';
import { ConfigService } from './services/config.service';
import { PostService } from './services/post.service';
import { PostComponent } from './main/post/post/post.component';
import {MatCardModule} from '@angular/material/card';
import { SignUpComponent } from './pages/sign-up/sign-up.component';
import { CommentListComponent } from './main/comment/comment-list/comment-list.component';
import { CommentItemComponent } from './main/comment/comment-item/comment-item.component';

@NgModule({
  declarations: [
    AppComponent,
    MainNavComponent,
    NavSearchComponent,
    PostListComponent,
    MainPageComponent,
    PostComponent,
    SignUpComponent,
    CommentListComponent,
    CommentItemComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    FormsModule,
    MatInputModule,
    HttpClientModule,
    MatCardModule
  ],
  providers: [
    // {
    //   provide: HTTP_INTERCEPTORS,
    //   useClass: TokenInterceptor,
    //   multi: true
    // },
    ApiService,
    ConfigService,
    PostService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
