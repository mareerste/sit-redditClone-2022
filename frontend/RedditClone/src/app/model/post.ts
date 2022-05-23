import { Observable } from 'rxjs';
import { Flair } from './flair';
import { User } from './user';
export class Post {
    id:number;
    title: string;
    text:string;
    creationDate:string;
    imagePath:string;
    isDeleted:boolean;
    user: User;
    flair: Flair;
    comments?:Comment[] | [];
}
