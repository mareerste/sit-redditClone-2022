import { CommunityService } from './service/community.service';
import { JwtUtilsService } from './service/jwt-utils.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import { MatInputModule } from '@angular/material/input';

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
import { NotifierService } from './service/notifier.service';


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
import { SinglePostComponent } from './pages/single-post/single-post.component';
import { CardComponent } from './components/card/card.component';
import { SingleCommunityComponent } from './pages/community/single-community/single-community/single-community.component';
import { CommunityListAtributeComponent } from './pages/community/single-community/community-list-atribute/community-list-atribute.component';
import { CommunityPostsComponent } from './pages/community/single-community/community-posts/community-posts.component';
import { CommunityEditComponent } from './pages/community/single-community/community-edit/community-edit.component';
import { CommunityListFlairsComponent } from './pages/community/single-community/community-list-flairs/community-list-flairs.component';
import { CommunityListModeratorsComponent } from './pages/community/single-community/community-list-moderators/community-list-moderators.component';
import { CommunityCreatePostComponent } from './pages/community/single-community/community-create-post/community-create-post.component';
import { CommunityCreateComponent } from './pages/community/community-create/community-create.component';
import { MembersBanListComponent } from './components/members-ban-list/members-ban-list.component';
import { MembersBanItemComponent } from './components/members-ban-item/members-ban-item.component';
import { CommunityFlairListComponent } from './components/community-flair-list/community-flair-list.component';
import { CommunityFlairItemComponent } from './components/community-flair-item/community-flair-item.component';
import { CommunityFlairFormComponent } from './components/community-flair-form/community-flair-form.component';
import { CommunityRuleItemComponent } from './components/community-rule-item/community-rule-item.component';
import { CommunityRuleListComponent } from './components/community-rule-list/community-rule-list.component';
import { CommunityRuleFormComponent } from './components/community-rule-form/community-rule-form.component';
import { ReportedPostsListComponent } from './components/reported-posts-list/reported-posts-list.component';
import { ReportedPostsItemComponent } from './components/reported-posts-item/reported-posts-item.component';
import { ReportedCommentsItemComponent } from './components/reported-comments-item/reported-comments-item.component';
import { ReportedCommentsListComponent } from './components/reported-comments-list/reported-comments-list.component';
import { ReportedPostsComponent } from './components/reported-posts/reported-posts.component';
import { ReportedPostsDetailsComponent } from './components/reported-posts-details/reported-posts-details.component';
import { ReportedCommentsDetailsComponent } from './components/reported-comments-details/reported-comments-details.component';
import { ReportedCommentsComponent } from './components/reported-comments/reported-comments.component';
import { ReportDialogComponent } from './components/report-dialog/report-dialog.component';

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
    SinglePostComponent,
    CardComponent,
    SingleCommunityComponent,
    CommunityListAtributeComponent,
    CommunityPostsComponent,
    CommunityEditComponent,
    CommunityListFlairsComponent,
    CommunityListModeratorsComponent,
    CommunityCreatePostComponent,
    CommunityCreateComponent,
    MembersBanListComponent,
    MembersBanItemComponent,
    CommunityFlairListComponent,
    CommunityFlairItemComponent,
    CommunityFlairFormComponent,
    CommunityRuleItemComponent,
    CommunityRuleListComponent,
    CommunityRuleFormComponent,
    ReportedPostsListComponent,
    ReportedPostsItemComponent,
    ReportedCommentsItemComponent,
    ReportedCommentsListComponent,
    ReportedPostsComponent,
    ReportedPostsDetailsComponent,
    ReportedCommentsDetailsComponent,
    ReportedCommentsComponent,
    ReportDialogComponent,
  ],
  entryComponents:[
    ReportDialogComponent,
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
    MatInputModule,
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
    ReactionService,
    JwtUtilsService,
    CommunityService,
    NotifierService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
