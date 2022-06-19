import { Injectable, Input } from '@angular/core';
import { MatSnackBar } from '@angular/material';

@Injectable({
  providedIn: 'root'
})
export class NotifierService {

  constructor(private snackBar: MatSnackBar) { }

  showNotification(text:any){
    this.snackBar.open(text,"",{
      duration:2000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
    })
  }
}
