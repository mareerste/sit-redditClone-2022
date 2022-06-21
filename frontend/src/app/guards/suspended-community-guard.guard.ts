import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CommunityService } from '../service/community.service';

@Injectable({
  providedIn: 'root'
})
export class SuspendedCommunityGuardGuard implements CanActivate {
  constructor(
    private communityService:CommunityService,
    private router:Router,
  ){

  }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      this.communityService.getCommunity(next.params.id).subscribe(data=>{
        if(data.suspended){
          this.router.navigate([''])
          return false;
        }
      })
    return true;
  }
  
}
