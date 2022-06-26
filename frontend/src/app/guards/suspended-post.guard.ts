import { PostService } from 'src/app/service';
import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SuspendedPostGuard implements CanActivate {
  constructor(
    private postService: PostService,
    private router: Router,
  ) { }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      this.postService.getPost2(next.params.id).subscribe(data => {
        return true
      },
      error => {
        if(error['status'] == 400){
          this.router.navigate([''])
          return false;
        }
      })
    return true
  }

}
